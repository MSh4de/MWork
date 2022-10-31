package eu.mshade.mwork.binarytag

import eu.mshade.mwork.PrettyString
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag

abstract class BinaryTag<T>(private val type: BinaryTagTypeKey, private val value: T) {

    open fun getType(): BinaryTagTypeKey {
        return type
    }

    fun getValue(): T {
        return value
    }

    override fun toString(): String {
        return "BinaryTag(type=$type, value=$value)"
    }

}


class EndBinaryTag : BinaryTag<Int>(BinaryTagType.END, 0) {

    companion object {
        val INSTANCE = EndBinaryTag()
    }
}

class ByteBinaryTag(value: Byte) : BinaryTag<Byte>(BinaryTagType.BYTE, value)

class ShortBinaryTag(value: Short) : BinaryTag<Short>(BinaryTagType.SHORT, value)

class IntegerBinaryTag(value: Int) : BinaryTag<Int>(BinaryTagType.INTEGER, value)

class LongBinaryTag(value: Long) : BinaryTag<Long>(BinaryTagType.LONG, value)

class FloatBinaryTag(value: Float) : BinaryTag<Float>(BinaryTagType.FLOAT, value)

class DoubleBinaryTag(value: Double) : BinaryTag<Double>(BinaryTagType.DOUBLE, value)

open class ByteArrayBinaryTag(binaryTagTypeKey: BinaryTagTypeKey = BinaryTagType.BYTE_ARRAY, value: ByteArray) :
    BinaryTag<ByteArray>(binaryTagTypeKey, value), PrettyString {

    constructor(value: ByteArray) : this(BinaryTagType.BYTE_ARRAY, value)

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append(this::class.simpleName)
        stringBuilder.append("{")
        stringBuilder.append(System.lineSeparator())
        getValue().take(10).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append(it)
            stringBuilder.append(System.lineSeparator())
        }
        if (getValue().size > 10) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("...")
            stringBuilder.append(System.lineSeparator())
        }

        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }

    override fun toString(): String {
        return "ByteArrayBinaryTag(value=${getValue().contentToString()})"
    }

}

class StringBinaryTag(value: String) : BinaryTag<String>(BinaryTagType.STRING, value)

open class IntegerArrayBinaryTag(binaryTagTypeKey: BinaryTagTypeKey = BinaryTagType.INTEGER_ARRAY, value: IntArray) :
    BinaryTag<IntArray>(binaryTagTypeKey, value), PrettyString {

    constructor(value: IntArray) : this(BinaryTagType.INTEGER_ARRAY, value)

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append(this::class.simpleName)
        stringBuilder.append("{")
        stringBuilder.append(System.lineSeparator())
        getValue().take(10).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append(it)
            stringBuilder.append(System.lineSeparator())
        }
        if (getValue().size > 10) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("...")
            stringBuilder.append(System.lineSeparator())
        }

        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }

    override fun toString(): String {
        return "IntegerArrayBinaryTag(value=${getValue().contentToString()})"
    }

}

open class LongArrayBinaryTag(binaryTagTypeKey: BinaryTagTypeKey = BinaryTagType.LONG_ARRAY, value: LongArray) :
    BinaryTag<LongArray>(binaryTagTypeKey, value), PrettyString {

    constructor(value: LongArray) : this(BinaryTagType.LONG_ARRAY, value)

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append(this::class.simpleName)
        stringBuilder.append("{")
        stringBuilder.append(System.lineSeparator())
        getValue().take(10).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append(it)
            stringBuilder.append(System.lineSeparator())
        }
        if (getValue().size > 10) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("...")
            stringBuilder.append(System.lineSeparator())
        }

        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }

    override fun toString(): String {
        return "LongArrayBinaryTag(value=${getValue().contentToString()})"
    }

}

class BooleanBinaryTag(value: Boolean) : BinaryTag<Boolean>(BinaryTagType.BOOLEAN, value)

class ZstdByteArrayBinaryTag(value: ByteArray) : ByteArrayBinaryTag(BinaryTagType.ZSTD_BYTE_ARRAY, value)

class ZstdListBinaryTag(elementBinaryTagTypeKey: BinaryTagTypeKey, value: MutableList<BinaryTag<*>> = mutableListOf()) :
    ListBinaryTag(BinaryTagType.ZSTD_LIST, elementBinaryTagTypeKey, value) {

        constructor(elementBinaryTagTypeKey: BinaryTagTypeKey) : this(elementBinaryTagTypeKey, mutableListOf())
    }

class ZstdCompoundBinaryTag(binaryTagMap: MutableMap<String, BinaryTag<*>> = mutableMapOf()) :
    CompoundBinaryTag(BinaryTagType.ZSTD_COMPOUND, binaryTagMap)

class ZstdIntegerArrayBinaryTag(value: IntArray) : IntegerArrayBinaryTag(BinaryTagType.ZSTD_INTEGER_ARRAY, value)

class ZstdLongArrayBinaryTag(value: LongArray) : LongArrayBinaryTag(BinaryTagType.ZSTD_LONG_ARRAY, value)

class DeflateByteArrayBinaryTag(value: ByteArray) : ByteArrayBinaryTag(BinaryTagType.DEFLATE_BYTE_ARRAY, value)

class DeflateListBinaryTag(elementBinaryTagTypeKey: BinaryTagTypeKey, value: MutableList<BinaryTag<*>> = mutableListOf()) :
    ListBinaryTag(BinaryTagType.DEFLATE_LIST, elementBinaryTagTypeKey, value)

class DeflateCompoundBinaryTag(binaryTagMap: MutableMap<String, BinaryTag<*>> = mutableMapOf()) :
    CompoundBinaryTag(BinaryTagType.DEFLATE_COMPOUND, binaryTagMap)

class DeflateIntegerArrayBinaryTag(value: IntArray) : IntegerArrayBinaryTag(BinaryTagType.DEFLATE_INTEGER_ARRAY, value)

class DeflateLongArrayBinaryTag(value: LongArray) : LongArrayBinaryTag(BinaryTagType.DEFLATE_LONG_ARRAY, value)


