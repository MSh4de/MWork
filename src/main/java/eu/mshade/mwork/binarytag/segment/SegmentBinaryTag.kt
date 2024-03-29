package eu.mshade.mwork.binarytag.segment

import eu.mshade.mwork.binarytag.*
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.file.StandardOpenOption
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ExecutionException
import java.util.function.Consumer

class SegmentBinaryTag(private val indexFile: File, dataFile: File, private val binaryTagDriver: BinaryTagDriver) {
    val compoundSectionIndex: SegmentSectionBucket
    private var asynchronousFileChannel: AsynchronousFileChannel? = null

    init {
        try {
            asynchronousFileChannel = AsynchronousFileChannel.open(
                dataFile.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.READ
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        compoundSectionIndex = readCompoundSectionIndex(indexFile)
    }

    @Throws(IOException::class)
    fun readCompoundBinaryTag(key: String): CompoundBinaryTag? {
        val binaryTagIndices = compoundSectionIndex.getSectionIndices(key) ?: return null

        // change by array
        val sectionIndices = arrayOfNulls<SegmentSection>(binaryTagIndices.size)
        binaryTagIndices.forEach(Consumer { segmentSection: SegmentSection ->
            sectionIndices[segmentSection.index] = segmentSection
        })
        val payload = ByteBuffer.allocate(getSizeOfSectionIndex(binaryTagIndices))
        for (i in binaryTagIndices.indices) {
            val carbonSection = sectionIndices[i]
            try {
                val byteBuffer = ByteBuffer.allocate(carbonSection!!.size)
                val read = asynchronousFileChannel!!.read(byteBuffer, carbonSection.start.toLong())
                read.get()
                payload.put(byteBuffer.array())
            } catch (e: InterruptedException) {
                LOGGER.error("Impossible to build data", e)
            } catch (e: ExecutionException) {
                LOGGER.error("Impossible to build data", e)
            }
        }
        return binaryTagDriver.readCompoundBinaryTag(ByteArrayInputStream(payload.array()))
    }

    @Throws(IOException::class, ExecutionException::class, InterruptedException::class)
    fun writeCompoundBinaryTag(key: String, compoundBinaryTag: CompoundBinaryTag?) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag!!, byteArrayOutputStream)
        val payload = byteArrayOutputStream.toByteArray()
        val sectionIndices = compoundSectionIndex.getSectionIndices(key) ?: ArrayList()
        if (sectionIndices.isNotEmpty()) {
            compoundSectionIndex.addFreeSectionIndices(sectionIndices)
            sectionIndices.clear()
            writeInFreeSectionIndices(payload, sectionIndices)
        } else {
            if (compoundSectionIndex.getFreeCarbonSection().isEmpty()) {
                val length = asynchronousFileChannel!!.size()
                val segmentSection = SegmentSection(0, length.toInt(), (length + payload.size).toInt())
                sectionIndices.add(segmentSection)
                asynchronousFileChannel!!.write(ByteBuffer.wrap(payload), length).get()
            } else {
                writeInFreeSectionIndices(payload, sectionIndices)
            }
        }
        compoundSectionIndex.setSectionIndex(key, sectionIndices)
    }

    @Throws(IOException::class, InterruptedException::class, ExecutionException::class)
    private fun writeInFreeSectionIndices(payload: ByteArray, sectionIndices: MutableList<SegmentSection>?) {
        var index = 0
        var binaryTagIndex = 0
        while (index != payload.size) {
            var carbonSection = compoundSectionIndex.poolFreeCarbonSection()
            if (carbonSection != null) {
                compoundSectionIndex.removeFreeSectionIndices(carbonSection)
                val i = payload.size - index
                var size = carbonSection.size
                if (carbonSection.size > i) {
                    size = i
                    val freeSegmentSection = SegmentSection(-1, carbonSection.start + size, carbonSection.end)
                    compoundSectionIndex.addFreeSectionIndices(freeSegmentSection)
                }
                carbonSection = SegmentSection(binaryTagIndex++, carbonSection.start, carbonSection.start + size)
                asynchronousFileChannel!!.write(ByteBuffer.wrap(payload, index, size), carbonSection.start.toLong())
                    .get()
                index += size
            } else {
                val length = asynchronousFileChannel!!.size()
                carbonSection =
                    SegmentSection(binaryTagIndex++, length.toInt(), (length + (payload.size - index)).toInt())
                asynchronousFileChannel!!.write(
                    ByteBuffer.wrap(payload, index, carbonSection.size),
                    carbonSection.start.toLong()
                ).get()
                index += carbonSection.size
            }
            sectionIndices!!.add(carbonSection)
        }
    }

    private fun getSizeOfSectionIndex(sectionIndices: Collection<SegmentSection>): Int {
        var size = 0
        for (carbonSection in sectionIndices) {
            size += carbonSection.size
        }
        return size
    }

    private fun readCompoundSectionIndex(file: File): SegmentSectionBucket {
        val segmentSectionBucket = SegmentSectionBucket()
        val compoundBinaryTag = binaryTagDriver.readCompoundBinaryTagOrDefault(file) { CompoundBinaryTag() }
        if (compoundBinaryTag.containsKey("carbonSection")) {
            val sectionIndices = compoundBinaryTag.getBinaryTag("carbonSection") as CompoundBinaryTag?
            sectionIndices!!.value.forEach { (s: String, binaryTag: BinaryTag<*>) ->
                val listBinaryTag = binaryTag as ListBinaryTag
                val binaryTagSectionIndices: MutableList<SegmentSection> = ArrayList()
                for (tag in listBinaryTag.value) {
                    val sectionIndex = tag as CompoundBinaryTag
                    val index = sectionIndex.getInt("index")
                    val start = sectionIndex.getInt("start")
                    val end = sectionIndex.getInt("end")
                    binaryTagSectionIndices.add(SegmentSection(index, start, end))
                }
                segmentSectionBucket.setSectionIndex(s, binaryTagSectionIndices)
            }
        }
        if (compoundBinaryTag.containsKey("freeCarbonSection")) {
            val freeSectionIndicesBinaryTag = compoundBinaryTag.getBinaryTag("freeCarbonSection") as ListBinaryTag?
            val freeSectionIndices = ConcurrentLinkedQueue<SegmentSection>()
            freeSectionIndicesBinaryTag!!.value.forEach(Consumer { binaryTag: BinaryTag<*> ->
                val sectionIndex = binaryTag as CompoundBinaryTag
                val start = sectionIndex.getInt("start")
                val end = sectionIndex.getInt("end")
                freeSectionIndices.add(SegmentSection(-1, start, end))
            })
            segmentSectionBucket.setFreeSectionIndices(freeSectionIndices)
        }
        return segmentSectionBucket
    }

    fun writeCompoundSectionIndex() {
        val compoundBinaryTag = CompoundBinaryTag()
        val compoundBinaryTagSectionIndices: CompoundBinaryTag = ZstdCompoundBinaryTag()
        compoundSectionIndex.getSectionIndicesByName().forEach { (s: String?, sectionIndices: List<SegmentSection>) ->
            val listBinaryTag: ListBinaryTag = ZstdListBinaryTag(BinaryTagType.COMPOUND)
            sectionIndices.forEach(Consumer { segmentSection: SegmentSection ->
                val compoundBinaryTagSectionIndex = CompoundBinaryTag()
                compoundBinaryTagSectionIndex.putInt("index", segmentSection.index)
                compoundBinaryTagSectionIndex.putInt("start", segmentSection.start)
                compoundBinaryTagSectionIndex.putInt("end", segmentSection.end)
                listBinaryTag.add(compoundBinaryTagSectionIndex)
            })
            compoundBinaryTagSectionIndices.putBinaryTag(s, listBinaryTag)
        }
        compoundBinaryTag.putBinaryTag("carbonSection", compoundBinaryTagSectionIndices)
        val binaryTagFreeSectionIndices: ListBinaryTag = ZstdListBinaryTag(BinaryTagType.COMPOUND)
        for (freeCarbonSection in compoundSectionIndex.getFreeCarbonSection()) {
            val compoundBinaryTagSectionIndex = CompoundBinaryTag()
            compoundBinaryTagSectionIndex.putInt("start", freeCarbonSection.start)
            compoundBinaryTagSectionIndex.putInt("end", freeCarbonSection.end)
            binaryTagFreeSectionIndices.add(compoundBinaryTagSectionIndex)
        }
        compoundBinaryTag.putBinaryTag("freeCarbonSection", binaryTagFreeSectionIndices)
        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, indexFile)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SegmentBinaryTag::class.java)
    }
}