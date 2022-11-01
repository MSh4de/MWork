package eu.mshade.mwork.binarytag

import eu.mshade.mwork.binarytag.buffer.*
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import java.io.*
import java.nio.charset.StandardCharsets
import java.util.function.Supplier

class BinaryTagDriver {

    private val binaryTagBufferByBinaryTagType: MutableMap<BinaryTagKey, BinaryTagBuffer> = HashMap()
    private val binaryTagMarshalByType: MutableMap<Class<*>, BinaryTagMarshal<Any>> = HashMap()

    init {
        binaryTagBufferByBinaryTagType[BinaryTagType.END] = EndBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.BYTE] = ByteBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.SHORT] = ShortBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.INT] =
            IntBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.LONG] = LongBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.FLOAT] = FloatBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.DOUBLE] = DoubleBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.STRING] = StringBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.BYTE_ARRAY] = ByteArrayBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.LIST] = ListBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.COMPOUND] = CompoundBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.INT_ARRAY] = IntegerArrayBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.LONG_ARRAY] = LongArrayBinaryTagBuffer()

        binaryTagBufferByBinaryTagType[BinaryTagType.BOOLEAN] = BooleanBinaryTagBuffer()

        binaryTagBufferByBinaryTagType[BinaryTagType.ZSTD_BYTE_ARRAY] = ZstdByteArrayBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.ZSTD_LIST] = ZstdListBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.ZSTD_COMPOUND] = ZstdCompoundBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.ZSTD_INT_ARRAY] = ZstdIntegerArrayBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.ZSTD_LONG_ARRAY] = ZstdLongArrayBinaryTagBuffer()

        binaryTagBufferByBinaryTagType[BinaryTagType.DEFLATE_BYTE_ARRAY] = DeflateByteArrayBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.DEFLATE_LIST] = DeflateListBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.DEFLATE_COMPOUND] = DeflateCompoundBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.DEFLATE_INT_ARRAY] = DeflateIntegerArrayBinaryTagBuffer()
        binaryTagBufferByBinaryTagType[BinaryTagType.DEFLATE_LONG_ARRAY] = DeflateLongArrayBinaryTagBuffer()

    }

    fun getBufferByType(binaryTagType: BinaryTagKey): BinaryTagBuffer? {
        return binaryTagBufferByBinaryTagType[binaryTagType]
    }

    fun writeCompoundBinaryTag(compoundBinaryTag: CompoundBinaryTag, file: File) {
        val fileOutputStream: FileOutputStream = try {
            FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        }
        writeCompoundBinaryTag(compoundBinaryTag, fileOutputStream)
    }

    fun writeCompoundBinaryTag(compoundBinaryTag: CompoundBinaryTag, outputStream: OutputStream) {
        try {
            DataOutputStream(outputStream).use { dataOutputStream ->
                val bytes = "".toByteArray(StandardCharsets.UTF_8)
                dataOutputStream.writeByte(compoundBinaryTag.getType().getIdentifier())
                dataOutputStream.writeShort(bytes.size)
                dataOutputStream.write(bytes)
                binaryTagBufferByBinaryTagType[compoundBinaryTag.getType()]!!
                    .write(this, dataOutputStream, compoundBinaryTag)
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun readCompoundBinaryTagOrDefault(file: File, supplier: Supplier<CompoundBinaryTag>): CompoundBinaryTag {
        return try {
            readCompoundBinaryTag(file)
        } catch (e: Exception) {
            supplier.get()
        }
    }

    fun readCompoundBinaryTagOrDefault(
        inputStream: InputStream,
        supplier: Supplier<CompoundBinaryTag>
    ): CompoundBinaryTag {
        return try {
            readCompoundBinaryTag(inputStream)
        } catch (e: Exception) {
            supplier.get()
        }
    }

    fun readCompoundBinaryTag(file: File): CompoundBinaryTag {
        val fileInputStream: FileInputStream = try {
            FileInputStream(file)
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        }
        return readCompoundBinaryTag(fileInputStream)
    }

    fun readCompoundBinaryTag(inputStream: InputStream): CompoundBinaryTag {
        try {
            DataInputStream(inputStream).use { dataInputStream ->
                val type: BinaryTagKey = BinaryTagType.getTagTypeById(dataInputStream.readByte())
                if (type != BinaryTagType.END) {
                    val length = dataInputStream.readShort()
                    val bytes = ByteArray(length.toInt())
                    dataInputStream.readFully(bytes)
                    return binaryTagBufferByBinaryTagType[type]!!.read(this, dataInputStream) as CompoundBinaryTag
                }
                return CompoundBinaryTag()
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun registerMarshal(binaryTagMarshal: BinaryTagMarshal<*>, vararg types: Class<*>) {
        for (type in types) {
            binaryTagMarshalByType[type] = binaryTagMarshal as BinaryTagMarshal<Any>
        }
    }

    fun <T> registerMarshal(type: Class<T>, binaryTagMarshal: BinaryTagMarshal<T>) {
        binaryTagMarshalByType[type] = binaryTagMarshal as BinaryTagMarshal<Any>
    }

    @JvmOverloads
    fun marshal(o: Any, type: Class<*> = o.javaClass): BinaryTag<*> {
        return try {
            binaryTagMarshalByType[type]!!.serialize(this, o)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun <T> unMarshal(binaryTag: BinaryTag<*>, type: Class<T>): T {
        return try {
            type.cast(binaryTagMarshalByType[type]!!.deserialize(this, binaryTag))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}