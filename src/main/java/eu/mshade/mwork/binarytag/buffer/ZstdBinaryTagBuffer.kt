package eu.mshade.mwork.binarytag.buffer

import com.github.luben.zstd.Zstd
import eu.mshade.mwork.binarytag.*
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import java.io.*
import java.util.function.Consumer
import java.util.function.Function

class ZstdByteArrayBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.BYTE_ARRAY)
        writeZstd(outputStream){
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.BYTE_ARRAY)
        return ZstdByteArrayBinaryTag(readZstd(inputStream){
            bufferByType!!.read(binaryTagDriver, it)
        }.value as ByteArray)
    }
}

class ZstdListBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LIST)
        writeZstd(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LIST)
        val listBinaryTag = readZstd(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        } as ListBinaryTag
        return ZstdListBinaryTag(listBinaryTag.elementType, listBinaryTag.value)
    }

}

class ZstdCompoundBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.COMPOUND)
        writeZstd(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.COMPOUND)
        val tagMap = readZstd(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        } as CompoundBinaryTag
        return ZstdCompoundBinaryTag(tagMap.value)
    }

}

class ZstdIntegerArrayBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.INT_ARRAY)
        writeZstd(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.INT_ARRAY)
        return ZstdIntArrayBinaryTag(readZstd(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        }.value as IntArray)
    }

}

class ZstdLongArrayBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LONG_ARRAY)
        writeZstd(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LONG_ARRAY)
        return ZstdLongArrayBinaryTag(readZstd(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        }.value as LongArray)
    }

}


private fun writeZstd(outputStream: DataOutputStream, consumer: Consumer<DataOutputStream>) {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(byteArrayOutputStream)
    consumer.accept(dataOutputStream)
    val compress = Zstd.compress(byteArrayOutputStream.toByteArray())
    outputStream.writeInt(byteArrayOutputStream.size())
    outputStream.writeInt(compress.size)
    outputStream.write(compress)
    dataOutputStream.close()
    byteArrayOutputStream.close()
}


private fun readZstd(inputStream: DataInputStream, consumer: Function<DataInputStream, BinaryTag<*>>): BinaryTag<*> {
    val realLength = inputStream.readInt()
    val length = inputStream.readInt()
    val payload = ByteArray(length)
    inputStream.readFully(payload)
    val byteArrayInputStream = ByteArrayInputStream(Zstd.decompress(payload, realLength))
    val dataInputStream = DataInputStream(byteArrayInputStream)
    return consumer.apply(dataInputStream)
}