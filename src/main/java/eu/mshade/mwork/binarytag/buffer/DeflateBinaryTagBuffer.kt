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

var inflater: Inflater = Inflater()
var deflater: Deflater = Deflater()

class DeflateByteArrayBinaryTagBuffer : BinaryTagBuffer {
    
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.BYTE_ARRAY)
        writeGzip(outputStream!!) {
            bufferByType!!.write(binaryTagDriver, outputStream, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.BYTE_ARRAY)
        return DeflateByteArrayBinaryTag(readGzip(inputStream!!) {
            bufferByType!!.read(binaryTagDriver, inputStream)
        }.getValue() as ByteArray)
    }

}

class DeflateListBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.LIST)
        writeGzip(outputStream!!) {
            bufferByType!!.write(binaryTagDriver, outputStream, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.LIST)
        val listBinaryTag = readGzip(inputStream!!) {
            bufferByType!!.read(binaryTagDriver, inputStream)
        }.getValue() as ListBinaryTag
        return DeflateListBinaryTag(listBinaryTag.elementType, listBinaryTag.getValue())
    }

}

class DeflateCompoundBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.COMPOUND)
        writeGzip(outputStream!!) {
            bufferByType!!.write(binaryTagDriver, outputStream, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.COMPOUND)
        val tagMap = readGzip(inputStream!!) {
            bufferByType!!.read(binaryTagDriver, inputStream)
        }.getValue() as MutableMap<String, BinaryTag<*>>
        return DeflateCompoundBinaryTag(tagMap)
    }

}

class DeflateIntegerArrayBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.INTEGER_ARRAY)
        writeGzip(outputStream!!) {
            bufferByType!!.write(binaryTagDriver, outputStream, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.INTEGER_ARRAY)
        return DeflateIntegerArrayBinaryTag(readGzip(inputStream!!) {
            bufferByType!!.read(binaryTagDriver, inputStream)
        }.getValue() as IntArray)
    }

}

class DeflateLongArrayBinaryTagBuffer : BinaryTagBuffer {
    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.LONG_ARRAY)
        writeGzip(outputStream!!) {
            bufferByType!!.write(binaryTagDriver, outputStream, binaryTag)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val bufferByType = binaryTagDriver!!.getBufferByType(BinaryTagType.LONG_ARRAY)
        return DeflateLongArrayBinaryTag(readGzip(inputStream!!) {
            bufferByType!!.read(binaryTagDriver, inputStream)
        }.getValue() as LongArray)
    }

}

private fun writeGzip(outputStream: DataOutputStream, consumer: Consumer<DataOutputStream>){
    val byteArrayOutputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(byteArrayOutputStream)
    consumer.accept(dataOutputStream)
    deflater.setInput(byteArrayOutputStream.toByteArray())
    deflater.finish()
    val buffer = ByteArray(1024)
    val compressedData = ByteArrayOutputStream()
    while (!deflater.finished()) {
        val count = deflater.deflate(buffer)
        compressedData.write(buffer, 0, count)
    }
    deflater.reset()
    outputStream.writeInt(compressedData.size())
    outputStream.write(compressedData.toByteArray())
    dataOutputStream.close()
    byteArrayOutputStream.close()
}

private fun readGzip(inputStream: DataInputStream, consumer: (DataInputStream) -> BinaryTag<*>): BinaryTag<*> {
    val length = inputStream.readInt()
    val payload = ByteArray(length)
    inputStream.readFully(payload)
    inflater.setInput(payload)
    val buffer = ByteArray(1024)
    val result = ByteArrayOutputStream()
    while (!inflater.finished()) {
        val count = inflater.inflate(buffer)
        result.write(buffer, 0, count)
    }
    inflater.reset()
    val byteArrayInputStream = ByteArrayInputStream(result.toByteArray())
    val dataInputStream = DataInputStream(byteArrayInputStream)
    dataInputStream.close()
    byteArrayInputStream.close()
    return consumer(dataInputStream)
}