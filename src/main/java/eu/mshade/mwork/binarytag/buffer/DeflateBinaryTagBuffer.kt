package eu.mshade.mwork.binarytag.buffer

import eu.mshade.mwork.binarytag.*
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.function.Consumer
import java.util.zip.Deflater
import java.util.zip.Inflater

class DeflateByteArrayBinaryTagBuffer : BinaryTagBuffer {
    
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.BYTE_ARRAY)
        writeDeflate(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.BYTE_ARRAY)
        return DeflateByteArrayBinaryTag(readDeflate(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        }.getValue() as ByteArray)
    }

}

class DeflateListBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LIST)
        writeDeflate(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LIST)
        val listBinaryTag = readDeflate(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        }.getValue() as ListBinaryTag
        return DeflateListBinaryTag(listBinaryTag.elementType, listBinaryTag.getValue())
    }

}

class DeflateCompoundBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.COMPOUND)
        writeDeflate(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.COMPOUND)
        val tagMap = readDeflate(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        }.getValue() as MutableMap<String, BinaryTag<*>>
        return DeflateCompoundBinaryTag(tagMap)
    }

}

class DeflateIntegerArrayBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.INT_ARRAY)
        writeDeflate(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.INT_ARRAY)
        return DeflateIntArrayBinaryTag(readDeflate(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        }.getValue() as IntArray)
    }

}

class DeflateLongArrayBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LONG_ARRAY)
        writeDeflate(outputStream) {
            bufferByType!!.write(binaryTagDriver, it, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver.getBufferByType(BinaryTagType.LONG_ARRAY)
        return DeflateLongArrayBinaryTag(readDeflate(inputStream) {
            bufferByType!!.read(binaryTagDriver, it)
        }.getValue() as LongArray)
    }

}

private fun writeDeflate(outputStream: DataOutputStream, consumer: Consumer<DataOutputStream>){
    val byteArrayOutputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(byteArrayOutputStream)
    val deflater = Deflater(9)
    consumer.accept(dataOutputStream)
    deflater.setInput(byteArrayOutputStream.toByteArray())
    deflater.finish()
    val buffer = ByteArray(1024)
    val compressedData = ByteArrayOutputStream()
    while (!deflater.finished()) {
        val count = deflater.deflate(buffer)
        compressedData.write(buffer, 0, count)
    }
    deflater.end()
    outputStream.writeInt(compressedData.size())
    outputStream.write(compressedData.toByteArray())
    dataOutputStream.close()
    byteArrayOutputStream.close()
}

private fun readDeflate(inputStream: DataInputStream, consumer: (DataInputStream) -> BinaryTag<*>): BinaryTag<*> {
    val length = inputStream.readInt()
    val payload = ByteArray(length)
    inputStream.readFully(payload)
    val inflater = Inflater()
    inflater.setInput(payload)
    val buffer = ByteArray(1024)
    val result = ByteArrayOutputStream()
    while (!inflater.finished()) {
        val count = inflater.inflate(buffer)
        result.write(buffer, 0, count)
    }
    inflater.end()
    val byteArrayInputStream = ByteArrayInputStream(result.toByteArray())
    val dataInputStream = DataInputStream(byteArrayInputStream)
    dataInputStream.close()
    byteArrayInputStream.close()
    return consumer(dataInputStream)
}