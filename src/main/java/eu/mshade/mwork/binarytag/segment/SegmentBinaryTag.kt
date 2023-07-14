package eu.mshade.mwork.binarytag.segment

import eu.mshade.mwork.binarytag.BinaryTagDriver
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import org.slf4j.LoggerFactory
import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.*
import java.util.concurrent.ExecutionException
import javax.swing.text.Segment
import kotlin.math.min

class SegmentBinaryTag(path: Path) {

    constructor(file: File) : this(file.toPath())

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SegmentBinaryTag::class.java)
    }

    private var file: AsynchronousFileChannel? = null
    private val segmentBlocks = mutableMapOf<String, SegmentBlock>()

    init {
        try {
            file = AsynchronousFileChannel.open(
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.READ
            )

            load()

        } catch (e: IOException) {
            LOGGER.error("Impossible to open file", e)
        }
    }

    fun read(key: ByteArray): ByteArray {
        if (file == null) {
            return ByteArray(0)
        }


        val stringKey = encodeKey(key)
        val segmentBlock = segmentBlocks[stringKey] ?: return ByteArray(0)

        val headers = segmentBlock.segmentHeaders
        val headersArray = arrayOfNulls<SegmentHeader>(headers.size)

        headers.forEach { segmentSection ->
            headersArray[segmentSection.index] = segmentSection
        }

        val payload = ByteBuffer.allocate(segmentBlock.getSpace())

        for (i in headers.indices) {
            val segmentHeader = headersArray[i] ?: continue

            try {
                if (segmentHeader.getPayloadSize() == 0) {
                    continue
                }

                val byteBuffer = ByteBuffer.allocate(segmentHeader.getPayloadSize())
                file?.read(byteBuffer, segmentHeader.getPayloadStart())?.get()
                payload.put(byteBuffer.array())

            } catch (e: InterruptedException) {

                LOGGER.error("Impossible to build data", e)

            } catch (e: ExecutionException) {

                LOGGER.error("Impossible to build data", e)

            }
        }

        return payload.array()
    }


    fun readCompound(key: String, binaryTagDriver: BinaryTagDriver): CompoundBinaryTag {
        return binaryTagDriver.readCompoundBinaryTag(ByteArrayInputStream(read(key.toByteArray())))
    }

    fun readCompound(key: ByteArray, binaryTagDriver: BinaryTagDriver): CompoundBinaryTag {
        return binaryTagDriver.readCompoundBinaryTag(ByteArrayInputStream(read(key)))
    }

    fun write(key: ByteArray, payload: ByteArray) {
        val stringKey = encodeKey(key)

        val segmentBlock = segmentBlocks.computeIfAbsent(stringKey) { _ -> SegmentBlock(key) }

        writePayload(key, payload, segmentBlock.segmentHeaders)
    }

    fun write(key: String, payload: ByteArray) {
        write(key.toByteArray(), payload)
    }

    fun writeCompound(key: ByteArray, binaryTagDriver: BinaryTagDriver, compoundBinaryTag: CompoundBinaryTag?) {
        if (compoundBinaryTag == null) {
            return
        }
        val byteArrayOutputStream = ByteArrayOutputStream()
        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, byteArrayOutputStream)
        write(key, byteArrayOutputStream.toByteArray())
    }

    fun writeCompound(key: String, binaryTagDriver: BinaryTagDriver, compoundBinaryTag: CompoundBinaryTag?) {
        writeCompound(key.toByteArray(), binaryTagDriver, compoundBinaryTag)
    }


    private fun writePayload(key: ByteArray, payload: ByteArray, segmentHeaders: MutableList<SegmentHeader>) {
        var index = 0
        var segmentHeaderIndex = 0

        val headersArray = arrayOfNulls<SegmentHeader>(segmentHeaders.size)

        segmentHeaders.forEach { segmentSection ->
            headersArray[segmentSection.index] = segmentSection
        }

        while (index != payload.size) {

            if (segmentHeaders.isEmpty()) {

                val segmentHeader = createSegment(index, key, payload, segmentHeaderIndex)
                segmentHeaders.add(segmentHeader)
                segmentHeaderIndex++
                index += segmentHeader.getPayloadSize()

            } else {

                if (segmentHeaderIndex < headersArray.size) {
                    val segmentHeader = headersArray[segmentHeaderIndex]!!
                    segmentHeaderIndex++

                    val maxSize =
                        min(index + (segmentHeader.end - segmentHeader.getPayloadStart()).toInt(), payload.size)

                    val splitPayload = payload.copyOfRange(index, maxSize)


                    file?.let {
                        segmentHeader.update(it, splitPayload).get()
                    }

                    index += splitPayload.size

                } else {

                    val segmentHeader = createSegment(index, key, payload, segmentHeaderIndex)
                    segmentHeaders.add(segmentHeader)
                    segmentHeaderIndex++
                    index += segmentHeader.getPayloadSize()
                }
            }
        }


        if (segmentHeaderIndex < headersArray.size) {
            for (i in segmentHeaderIndex until headersArray.size) {
                file?.let {
                    headersArray[i]?.update(it, ByteArray(0))?.get()
                }
            }
        }
    }

    fun hasKey(key: ByteArray): Boolean {
        val stringKey = encodeKey(key)
        return segmentBlocks.containsKey(stringKey)
    }

    fun hasKey(key: String): Boolean {
        return hasKey(key.toByteArray())
    }

    private fun createSegment(index: Int, key: ByteArray, payload: ByteArray, segmentHeaderIndex: Int): SegmentHeader {
        val start = file?.size() ?: 0L
        val splitPayload = payload.copyOfRange(index, payload.size)

        val segmentHeader =
            SegmentHeader(
                splitPayload.size + Int.SIZE_BYTES * 4 + key.size,
                key,
                segmentHeaderIndex,
                splitPayload.size,
                start
            )


        file?.let {
            segmentHeader.write(it, splitPayload).get()
        }


        return segmentHeader
    }

    private fun load() {

        val file = file ?: return

        val segmentHeaders = mutableListOf<SegmentHeader>()

        val size = file.size()
        var index = 0L
        while (index < size) {

            val start = index
            val byteBufferAllocatedSize = read(index, Int.SIZE_BYTES)

            val allocatedSize = byteBufferAllocatedSize.readInt()

            val byteBuffer = read(index + Int.SIZE_BYTES, allocatedSize)
            val keySize = byteBuffer.readInt()
            val key = ByteArray(keySize)
            byteBuffer.read(key)
            val segmentIndex = byteBuffer.readInt()
            val payloadSize = byteBuffer.readInt()

            val segmentHeader = SegmentHeader(allocatedSize, key, segmentIndex, payloadSize, start)

            segmentHeaders.add(segmentHeader)

            index += allocatedSize
        }

        segmentHeaders.forEach {
            val stringKey = encodeKey(it.key)
            segmentBlocks.computeIfAbsent(stringKey) { _ -> SegmentBlock(it.key) }.segmentHeaders.add(it)
        }


    }

    private fun read(cursor: Long, size: Int): DataInputStream {
        val byteBuffer = ByteBuffer.allocate(size)
        file?.read(byteBuffer, cursor)?.get()
        return DataInputStream(ByteArrayInputStream(byteBuffer.array()))
    }

    private fun encodeKey(key: ByteArray): String {
        return Base64.getEncoder().encodeToString(key)
    }



    fun getSegmentBlocks(): Collection<SegmentBlock> {
        return segmentBlocks.values
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SegmentBinaryTag) return false

        return file == other.file
    }

    override fun hashCode(): Int {
        return Objects.hash(file)
    }
}