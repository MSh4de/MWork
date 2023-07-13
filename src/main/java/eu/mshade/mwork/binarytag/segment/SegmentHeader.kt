package eu.mshade.mwork.binarytag.segment

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.util.concurrent.Future

data class SegmentHeader(
    val allocatedSize: Int,
    val key: ByteArray,
    val index: Int,
    private var payloadSize: Int,
    val start: Long
) {

    val end: Long
        get() = start + allocatedSize

    fun getPayloadSize(): Int {
        return payloadSize
    }


    fun getSpace(): Int {
        return allocatedSize + Int.SIZE_BYTES * 4 + key.size
    }

    fun getPayloadStart(): Long {
        return start + Int.SIZE_BYTES * 4 + key.size
    }

    fun getRealPayloadSize(): Int {
        return allocatedSize - Int.SIZE_BYTES * 4 - key.size
    }

    fun update(file: AsynchronousFileChannel, bytes: ByteArray): Future<Int> {
        payloadSize = bytes.size

        val byteArrayOutputStream = ByteArrayOutputStream()
        val dataOutputStream = DataOutputStream(byteArrayOutputStream)
        dataOutputStream.writeInt(payloadSize)
        dataOutputStream.write(bytes)

        return file.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()), getPayloadStart() - Int.SIZE_BYTES)
    }

    fun write(file: AsynchronousFileChannel, bytes: ByteArray): Future<Int> {
        payloadSize = bytes.size

        val byteArrayOutputStream = ByteArrayOutputStream()
        byteArrayOutputStream.write(toByteArray())
        byteArrayOutputStream.write(bytes)

        return file.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()), start)
    }

    fun toByteArray(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val dataOutputStream = DataOutputStream(byteArrayOutputStream)
        dataOutputStream.writeInt(allocatedSize)
        dataOutputStream.writeInt(key.size)
        dataOutputStream.write(key)
        dataOutputStream.writeInt(index)
        dataOutputStream.writeInt(payloadSize)
        return byteArrayOutputStream.toByteArray()
    }

    companion object {
        fun fromByteArray(size: Int, start: Long, byteArray: ByteArray): SegmentHeader {
            val byteArrayInputStream = ByteArrayInputStream(byteArray)
            val dataInputStream = DataInputStream(byteArrayInputStream)
            val keySize = dataInputStream.readInt()
            val key = ByteArray(keySize)
            dataInputStream.read(key)
            val index = dataInputStream.readInt()
            val payloadSize = dataInputStream.readInt()
            return SegmentHeader(size, key, index, payloadSize, start)
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SegmentHeader) return false

        if (allocatedSize != other.allocatedSize) return false
        if (!key.contentEquals(other.key)) return false
        if (index != other.index) return false
        return payloadSize == other.payloadSize
    }

    override fun hashCode(): Int {
        var result = allocatedSize.hashCode()
        result = 31 * result + key.contentHashCode()
        result = 31 * result + index
        result = 31 * result + payloadSize
        return result
    }

}