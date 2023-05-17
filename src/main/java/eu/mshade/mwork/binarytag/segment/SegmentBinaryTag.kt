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
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ExecutionException
import kotlin.math.min

class SegmentBinaryTag(path: Path) {


    constructor(file: File) : this(file.toPath())

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SegmentBinaryTag::class.java)
    }

    private var asynchronousFileChannel: AsynchronousFileChannel? = null
    private val segmentBlocks = mutableMapOf<String, SegmentBlock>()

    init {
        try {
            asynchronousFileChannel = AsynchronousFileChannel.open(
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.READ
            )

            load()

        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun read(key: ByteArray): ByteArray {
        val stringKey = Base64.getEncoder().encodeToString(key)
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
                val read = asynchronousFileChannel!!.read(byteBuffer, segmentHeader.getPayloadStart())
                read.get()
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
        val stringKey = Base64.getEncoder().encodeToString(key)

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
                val start = asynchronousFileChannel?.size() ?: 0L
                val splitPayload = payload.copyOfRange(index, payload.size)

                val segmentHeader =
                    SegmentHeader(
                        splitPayload.size + Int.SIZE_BYTES * 4 + key.size,
                        key,
                        segmentHeaderIndex++,
                        splitPayload.size,
                        start
                    )

                segmentHeaders.add(segmentHeader)

                asynchronousFileChannel?.let {
                    segmentHeader.write(it, splitPayload).get()
                }

                index += splitPayload.size
            } else {

                if (segmentHeaderIndex < headersArray.size) {
                    val segmentHeader = headersArray[segmentHeaderIndex]!!
                    segmentHeaderIndex++

                    val maxSize =
                        min(index + (segmentHeader.end - segmentHeader.getPayloadStart()).toInt(), payload.size)

                    val splitPayload =
                        payload.copyOfRange(index, maxSize)


                    asynchronousFileChannel?.let {
                        segmentHeader.update(it, splitPayload).get()
                    }

                    index += splitPayload.size

                } else {

                    val start = asynchronousFileChannel?.size() ?: 0L
                    val splitPayload = payload.copyOfRange(index, payload.size)

                    val segmentHeader =
                        SegmentHeader(
                            splitPayload.size + Int.SIZE_BYTES * 4 + key.size,
                            key,
                            segmentHeaderIndex++,
                            splitPayload.size,
                            start
                        )

                    segmentHeaders.add(segmentHeader)

                    asynchronousFileChannel?.let {
                        segmentHeader.write(it, splitPayload).get()
                    }

                    index += splitPayload.size
                }
            }
        }


        if (segmentHeaderIndex < headersArray.size) {
            for (i in segmentHeaderIndex until headersArray.size) {
                asynchronousFileChannel?.let {
                    headersArray[i]?.update(it, ByteArray(0))?.get()
                }
            }
        }
    }

    fun hasKey(key: ByteArray): Boolean {
        val stringKey = Base64.getEncoder().encodeToString(key)
        return segmentBlocks.containsKey(stringKey)
    }

    fun hasKey(key: String): Boolean {
        return hasKey(key.toByteArray())
    }

    private fun load() {

        val file = asynchronousFileChannel ?: return

        val segmentHeaders = ConcurrentLinkedQueue<SegmentHeader>()

        val size = file.size()
        var index = 0L
        while (index < size) {

            val start = index
            val byteBufferAllocatedSize = read(index, Int.SIZE_BYTES * 2)


            val allocatedSize = byteBufferAllocatedSize.readInt()
            val keySize = byteBufferAllocatedSize.readInt()


            val byteBuffer = read(index + Int.SIZE_BYTES * 2, keySize + Int.SIZE_BYTES * 2)
            val key = ByteArray(keySize)
            byteBuffer.read(key)
            val segmentIndex = byteBuffer.readInt()
            val payloadSize = byteBuffer.readInt()

            val segmentHeader = SegmentHeader(allocatedSize, key, segmentIndex, payloadSize, start)

            segmentHeaders.add(segmentHeader)

            index += allocatedSize
        }

        segmentHeaders.forEach {
            val stringKey = Base64.getEncoder().encodeToString(it.key)
            segmentBlocks.computeIfAbsent(stringKey) { _ -> SegmentBlock(it.key) }.segmentHeaders.add(it)
        }


    }

    private fun read(cursor: Long, size: Int): DataInputStream {
        val byteBuffer = ByteBuffer.allocate(size)
        asynchronousFileChannel?.read(byteBuffer, cursor)?.get()
        return DataInputStream(ByteArrayInputStream(byteBuffer.array()))
    }

}

data class SegmentBlock(val key: ByteArray, val segmentHeaders: MutableList<SegmentHeader> = mutableListOf()) {

    fun getSpace(): Int {
        return segmentHeaders.sumOf { it.getPayloadSize() }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SegmentBlock) return false

        if (!key.contentEquals(other.key)) return false
        return segmentHeaders == other.segmentHeaders
    }

    override fun hashCode(): Int {
        var result = key.contentHashCode()
        result = 31 * result + segmentHeaders.hashCode()
        return result
    }
}