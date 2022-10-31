package eu.mshade.mwork.binarytag

import eu.mshade.mwork.binarytag.entity.*

object BinaryTagType {

    val END = BinaryTagTypeKey.from(0, EndBinaryTag::class)
    val BYTE = BinaryTagTypeKey.from(1, ByteBinaryTag::class)
    val SHORT = BinaryTagTypeKey.from(2, ShortBinaryTag::class)
    val INTEGER = BinaryTagTypeKey.from(3, IntegerBinaryTag::class)
    val LONG = BinaryTagTypeKey.from(4, LongBinaryTag::class)
    val FLOAT = BinaryTagTypeKey.from(5, FloatBinaryTag::class)
    val DOUBLE = BinaryTagTypeKey.from(6, DoubleBinaryTag::class)
    val BYTE_ARRAY = BinaryTagTypeKey.from(7, ByteArrayBinaryTag::class)
    val STRING = BinaryTagTypeKey.from(8, StringBinaryTag::class)
    val LIST = BinaryTagTypeKey.from(9, ListBinaryTag::class)
    val COMPOUND = BinaryTagTypeKey.from(10, CompoundBinaryTag::class)
    val INTEGER_ARRAY = BinaryTagTypeKey.from(11, IntegerArrayBinaryTag::class)
    val LONG_ARRAY = BinaryTagTypeKey.from(12, LongArrayBinaryTag::class)

    val BOOLEAN = BinaryTagTypeKey.from(13, BooleanBinaryTag::class)

    val ZSTD_BYTE_ARRAY = BinaryTagTypeKey.from(14, ZstdByteArrayBinaryTag::class)
    val ZSTD_LIST = BinaryTagTypeKey.from(15, ZstdListBinaryTag::class)
    val ZSTD_COMPOUND = BinaryTagTypeKey.from(16, ZstdCompoundBinaryTag::class)
    val ZSTD_INTEGER_ARRAY = BinaryTagTypeKey.from(17, ZstdIntegerArrayBinaryTag::class)
    val ZSTD_LONG_ARRAY = BinaryTagTypeKey.from(18, ZstdLongArrayBinaryTag::class)

    val DEFLATE_BYTE_ARRAY = BinaryTagTypeKey.from(19, DeflateByteArrayBinaryTag::class)
    val DEFLATE_LIST = BinaryTagTypeKey.from(20, DeflateListBinaryTag::class)
    val DEFLATE_COMPOUND = BinaryTagTypeKey.from(21, DeflateCompoundBinaryTag::class)
    val DEFLATE_INTEGER_ARRAY = BinaryTagTypeKey.from(22, DeflateIntegerArrayBinaryTag::class)
    val DEFLATE_LONG_ARRAY = BinaryTagTypeKey.from(23, DeflateLongArrayBinaryTag::class)


    private val binaryTagTypeById = mutableMapOf<Int, BinaryTagTypeKey>()

    init {
        BinaryTagType::class.java.declaredFields.forEach { field ->
            var obj = field.get(null)
            if (obj is BinaryTagTypeKey) {
                binaryTagTypeById[obj.getIdentifier()] = obj
            }
        }
    }

    fun getTagTypeById(id: Int): BinaryTagTypeKey {
        return binaryTagTypeById[id] ?: throw IllegalArgumentException("Unknown BinaryTagTypeKey with id $id")
    }

    fun getTagTypeById(id: Byte): BinaryTagTypeKey = getTagTypeById(id.toInt())



}