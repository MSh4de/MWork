package eu.mshade.mwork.binarytag

import eu.mshade.mwork.PrettyString
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag

abstract class BinaryTag<T>(private val type: BinaryTagKey, private val value: T) {

    open fun getType(): BinaryTagKey {
        return type
    }

    fun getValue(): T {
        return value
    }

    override fun toString(): String {
        return "BinaryTag(type=${type.getName()}, value=$value)"
    }

}


class EndBinaryTag : BinaryTag<Int>(BinaryTagType.END, 0) {

    companion object {
        val INSTANCE = EndBinaryTag()
    }
}

class ByteBinaryTag(value: Byte) : BinaryTag<Byte>(BinaryTagType.BYTE, value)

class ShortBinaryTag(value: Short) : BinaryTag<Short>(BinaryTagType.SHORT, value)

class IntBinaryTag(value: Int) : BinaryTag<Int>(BinaryTagType.INT, value)

class LongBinaryTag(value: Long) : BinaryTag<Long>(BinaryTagType.LONG, value)

class FloatBinaryTag(value: Float) : BinaryTag<Float>(BinaryTagType.FLOAT, value)

class DoubleBinaryTag(value: Double) : BinaryTag<Double>(BinaryTagType.DOUBLE, value)

open class ByteArrayBinaryTag(binaryTagKey: BinaryTagKey = BinaryTagType.BYTE_ARRAY, value: ByteArray) :
    BinaryTag<ByteArray>(binaryTagKey, value), PrettyString {

    constructor(value: ByteArray) : this(BinaryTagType.BYTE_ARRAY, value)

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append(this::class.simpleName)
        stringBuilder.append("{")
        stringBuilder.append(System.lineSeparator())
        getValue().take(10).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("-").append(' ')
            stringBuilder.append(it)
            stringBuilder.append(System.lineSeparator())
        }
        if (getValue().size > 10) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("+ ${getValue().size - 10} more")
            stringBuilder.append(System.lineSeparator())
        }

        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }

    override fun toString(): String {
        return "${this::class.simpleName}(value=${getValue().contentToString()})"
    }

}

class StringBinaryTag(value: String) : BinaryTag<String>(BinaryTagType.STRING, value)

open class IntArrayBinaryTag(binaryTagKey: BinaryTagKey = BinaryTagType.INT_ARRAY, value: IntArray) :
    BinaryTag<IntArray>(binaryTagKey, value), PrettyString {

    constructor(value: IntArray) : this(BinaryTagType.INT_ARRAY, value)

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append(this::class.simpleName)
        stringBuilder.append("{")
        stringBuilder.append(System.lineSeparator())
        getValue().take(10).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("-").append(' ')
            stringBuilder.append(it)
            stringBuilder.append(System.lineSeparator())
        }
        if (getValue().size > 10) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("+ ${getValue().size - 10} more")
            stringBuilder.append(System.lineSeparator())
        }

        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }


    override fun toString(): String {
        return "${this::class.simpleName}(value=${getValue().contentToString()})"
    }

}

open class LongArrayBinaryTag(binaryTagKey: BinaryTagKey = BinaryTagType.LONG_ARRAY, value: LongArray) :
    BinaryTag<LongArray>(binaryTagKey, value), PrettyString {

    constructor(value: LongArray) : this(BinaryTagType.LONG_ARRAY, value)

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append(this::class.simpleName)
        stringBuilder.append("{")
        stringBuilder.append(System.lineSeparator())
        getValue().take(10).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("-").append(' ')
            stringBuilder.append(it)
            stringBuilder.append(System.lineSeparator())
        }
        if (getValue().size > 10) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("+ ${getValue().size - 10} more")
            stringBuilder.append(System.lineSeparator())
        }

        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }


    override fun toString(): String {
        return "${this::class.simpleName}(value=${getValue().contentToString()})"
    }

}

class BooleanBinaryTag(value: Boolean) : BinaryTag<Boolean>(BinaryTagType.BOOLEAN, value)

class ZstdByteArrayBinaryTag(value: ByteArray) : ByteArrayBinaryTag(BinaryTagType.ZSTD_BYTE_ARRAY, value)

class ZstdListBinaryTag(elementBinaryTagKey: BinaryTagKey, value: MutableList<BinaryTag<*>> = mutableListOf()) :
    ListBinaryTag(BinaryTagType.ZSTD_LIST, elementBinaryTagKey, value) {

    constructor(elementBinaryTagKey: BinaryTagKey) : this(elementBinaryTagKey, mutableListOf())
}

class ZstdCompoundBinaryTag(binaryTagMap: MutableMap<String, BinaryTag<*>> = mutableMapOf()) :
    CompoundBinaryTag(BinaryTagType.ZSTD_COMPOUND, binaryTagMap)

class ZstdIntArrayBinaryTag(value: IntArray) : IntArrayBinaryTag(BinaryTagType.ZSTD_INT_ARRAY, value)

class ZstdLongArrayBinaryTag(value: LongArray) : LongArrayBinaryTag(BinaryTagType.ZSTD_LONG_ARRAY, value)

class DeflateByteArrayBinaryTag(value: ByteArray) : ByteArrayBinaryTag(BinaryTagType.DEFLATE_BYTE_ARRAY, value)

class DeflateListBinaryTag(
    elementBinaryTagKey: BinaryTagKey,
    value: MutableList<BinaryTag<*>> = mutableListOf()
) :
    ListBinaryTag(BinaryTagType.DEFLATE_LIST, elementBinaryTagKey, value) {

    constructor(elementBinaryTagKey: BinaryTagKey) : this(elementBinaryTagKey, mutableListOf())
}

class DeflateCompoundBinaryTag(binaryTagMap: MutableMap<String, BinaryTag<*>> = mutableMapOf()) :
    CompoundBinaryTag(BinaryTagType.DEFLATE_COMPOUND, binaryTagMap)

class DeflateIntArrayBinaryTag(value: IntArray) : IntArrayBinaryTag(BinaryTagType.DEFLATE_INT_ARRAY, value)

class DeflateLongArrayBinaryTag(value: LongArray) : LongArrayBinaryTag(BinaryTagType.DEFLATE_LONG_ARRAY, value)


