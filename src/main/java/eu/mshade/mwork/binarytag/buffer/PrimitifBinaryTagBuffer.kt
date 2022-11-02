package eu.mshade.mwork.binarytag.buffer

import eu.mshade.mwork.binarytag.*
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import java.io.DataInputStream
import java.io.DataOutputStream
import java.nio.charset.StandardCharsets


class EndBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeByte(0)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        inputStream.readByte()
        return EndBinaryTag.INSTANCE
    }

}

class ByteBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeByte(binaryTag.value as Int)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        return ByteBinaryTag(inputStream.readByte())
    }

}

class ShortBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeShort(binaryTag.value as Int)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        return ShortBinaryTag(inputStream.readShort())
    }

}

class IntBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeInt(binaryTag.value as Int)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        return IntBinaryTag(inputStream.readInt())
    }

}

class LongBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeLong(binaryTag.value as Long)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        return LongBinaryTag(inputStream.readLong())
    }

}

class FloatBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeFloat(binaryTag.value as Float)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        return FloatBinaryTag(inputStream.readFloat())
    }
}

class DoubleBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeDouble(binaryTag.value as Double)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        return DoubleBinaryTag(inputStream.readDouble())
    }

}

class ByteArrayBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val byteArray = binaryTag.value as ByteArray
        outputStream.writeInt(byteArray.size)
        outputStream.write(byteArray)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val size = inputStream.readInt()
        val byteArray = ByteArray(size)
        inputStream.read(byteArray)
        return ByteArrayBinaryTag(byteArray)
    }

}

class StringBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val string = binaryTag.value as String
        val bytes: ByteArray = string.toByteArray(StandardCharsets.UTF_8)
        outputStream.writeShort(bytes.size)
        outputStream.write(bytes)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val length = inputStream.readShort()
        val bytes = ByteArray(length.toInt())
        inputStream.readFully(bytes)
        return StringBinaryTag(String(bytes, StandardCharsets.UTF_8))
    }

}

class ListBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val listBinaryTag = binaryTag as ListBinaryTag
        outputStream.writeByte(listBinaryTag.elementType.getIdentifier())
        outputStream.writeInt(listBinaryTag.size())
        val bufferByType = binaryTagDriver.getBufferByType(listBinaryTag.elementType)
        listBinaryTag.value.forEach{
            bufferByType!!.write(binaryTagDriver, outputStream, it)
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val tagType: BinaryTagKey = BinaryTagType.getTagTypeById(inputStream.readByte())!!
        val listBinaryTag = ListBinaryTag(tagType)
        val binaryTagBuffer = binaryTagDriver.getBufferByType(tagType)
        val length = inputStream.readInt()
        for (i in 0 until length) {
            listBinaryTag.add(binaryTagBuffer!!.read(binaryTagDriver, inputStream))
        }
        return listBinaryTag
    }

}

class CompoundBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val compoundBinaryTag = binaryTag as CompoundBinaryTag
        for (entry in compoundBinaryTag.value) {
            outputStream.writeByte(entry.value.type.getIdentifier())
            outputStream.writeUTF(entry.key)
            binaryTagDriver.getBufferByType(entry.value.type)!!.write(binaryTagDriver, outputStream, entry.value)
        }
        outputStream.write(0)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): CompoundBinaryTag {
        val compoundBinaryTag = CompoundBinaryTag()
        var tagTypeById: BinaryTagKey?
        do {
            val b = inputStream.readByte()
            tagTypeById = BinaryTagType.getTagTypeById(b)
            if (tagTypeById != BinaryTagType.END) {
                val length = inputStream.readShort()
                val bytes = ByteArray(length.toInt())
                inputStream.readFully(bytes)
                val name = String(bytes, StandardCharsets.UTF_8)
                compoundBinaryTag.putBinaryTag(name, binaryTagDriver.getBufferByType(tagTypeById)!!
                    .read(binaryTagDriver, inputStream))
            }
        } while (tagTypeById != BinaryTagType.END)
        return compoundBinaryTag
    }

}

class IntegerArrayBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val intArray = binaryTag.value as IntArray
        outputStream.writeInt(intArray.size)
        for (i in intArray.indices) {
            outputStream.writeInt(intArray[i])
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val size = inputStream.readInt()
        val intArray = IntArray(size)
        for (i in 0 until size) {
            intArray[i] = inputStream.readInt()
        }
        return IntArrayBinaryTag(intArray)
    }

}

class LongArrayBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        val longArray = binaryTag!!.value as LongArray
        outputStream.writeInt(longArray.size)
        for (i in longArray.indices) {
            outputStream.writeLong(longArray[i])
        }
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        val size = inputStream.readInt()
        val longArray = LongArray(size)
        for (i in 0 until size) {
            longArray[i] = inputStream.readLong()
        }
        return LongArrayBinaryTag(longArray)
    }

}

class BooleanBinaryTagBuffer : BinaryTagBuffer {

    override fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>) {
        outputStream.writeBoolean(binaryTag!!.value as Boolean)
    }

    override fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*> {
        return BooleanBinaryTag(inputStream.readBoolean())
    }

}