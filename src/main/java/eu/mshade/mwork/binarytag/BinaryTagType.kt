package eu.mshade.mwork.binarytag

import eu.mshade.mwork.binarytag.entity.*

object BinaryTagType {

    @JvmField val END = BinaryTagKey.from(0, "End",EndBinaryTag::class)
    @JvmField val BYTE = BinaryTagKey.from(1, "Byte", ByteBinaryTag::class)
    @JvmField val SHORT = BinaryTagKey.from(2, "Short", ShortBinaryTag::class)
    @JvmField val INT = BinaryTagKey.from(3, "Int", IntBinaryTag::class)
    @JvmField val LONG = BinaryTagKey.from(4, "Long", LongBinaryTag::class)
    @JvmField val FLOAT = BinaryTagKey.from(5, "Float", FloatBinaryTag::class)
    @JvmField val DOUBLE = BinaryTagKey.from(6, "Double", DoubleBinaryTag::class)
    @JvmField val BYTE_ARRAY = BinaryTagKey.from(7, "ByteArray", ByteArrayBinaryTag::class)
    @JvmField val STRING = BinaryTagKey.from(8, "String", StringBinaryTag::class)
    @JvmField val LIST = BinaryTagKey.from(9, "List", ListBinaryTag::class)
    @JvmField val COMPOUND = BinaryTagKey.from(10, "Compound", CompoundBinaryTag::class)
    @JvmField val INT_ARRAY = BinaryTagKey.from(11, "IntArray", IntArrayBinaryTag::class)
    @JvmField val LONG_ARRAY = BinaryTagKey.from(12, "LongArray", LongArrayBinaryTag::class)

    @JvmField val BOOLEAN = BinaryTagKey.from(13, "Boolean", BooleanBinaryTag::class)

    @JvmField val ZSTD_BYTE_ARRAY = BinaryTagKey.from(14, "ZstdByteArray", ZstdByteArrayBinaryTag::class)
    @JvmField val ZSTD_LIST = BinaryTagKey.from(15, "ZstdList", ZstdListBinaryTag::class)
    @JvmField val ZSTD_COMPOUND = BinaryTagKey.from(16, "ZstdCompound", ZstdCompoundBinaryTag::class)
    @JvmField val ZSTD_INT_ARRAY = BinaryTagKey.from(17, "ZstdIntArray", ZstdIntArrayBinaryTag::class)
    @JvmField val ZSTD_LONG_ARRAY = BinaryTagKey.from(18, "ZstdLongArray", ZstdLongArrayBinaryTag::class)

    @JvmField val DEFLATE_BYTE_ARRAY = BinaryTagKey.from(19, "DeflateByteArray", DeflateByteArrayBinaryTag::class)
    @JvmField val DEFLATE_LIST = BinaryTagKey.from(20, "DeflateList", DeflateListBinaryTag::class)
    @JvmField val DEFLATE_COMPOUND = BinaryTagKey.from(21, "DeflateCompound", DeflateCompoundBinaryTag::class)
    @JvmField val DEFLATE_INT_ARRAY = BinaryTagKey.from(22, "DeflateIntArray", DeflateIntArrayBinaryTag::class)
    @JvmField val DEFLATE_LONG_ARRAY = BinaryTagKey.from(23, "DeflateLongArray", DeflateLongArrayBinaryTag::class)


    private val binaryTagTypeById = mutableMapOf<Int, BinaryTagKey>()

    init {
        BinaryTagType::class.java.declaredFields.forEach { field ->
            val obj = field.get(null)
            if (obj is BinaryTagKey) {
                binaryTagTypeById[obj.getIdentifier()] = obj
            }
        }
    }

    fun getTagTypeById(id: Int): BinaryTagKey {
        return binaryTagTypeById[id] ?: throw IllegalArgumentException("Unknown BinaryTagTypeKey with id $id")
    }

    fun getTagTypeById(id: Byte): BinaryTagKey = getTagTypeById(id.toInt())

    fun registerTagType(binaryTagKey: BinaryTagKey) {
        binaryTagTypeById[binaryTagKey.getIdentifier()] = binaryTagKey
    }



}