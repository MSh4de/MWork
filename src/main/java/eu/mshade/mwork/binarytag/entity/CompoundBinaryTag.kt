package eu.mshade.mwork.binarytag.entity

import eu.mshade.mwork.PrettyString
import eu.mshade.mwork.binarytag.*
import java.util.*
import java.util.function.Function

open class CompoundBinaryTag(binaryTagTypeKey: BinaryTagTypeKey = BinaryTagType.COMPOUND, private val binaryTagMap: MutableMap<String, BinaryTag<*>> = mutableMapOf()) :
    BinaryTag<MutableMap<String, BinaryTag<*>>>(
        binaryTagTypeKey, binaryTagMap
    ), PrettyString {

    constructor() : this(BinaryTagType.COMPOUND, mutableMapOf())


    fun putBinaryTag(key: String, value: BinaryTag<*>): CompoundBinaryTag {
        binaryTagMap[key] = value
        return this
    }

    fun getBinaryTag(key: String): BinaryTag<*>? {
        return binaryTagMap[key]
    }

    fun addCompound(compoundBinaryTag: CompoundBinaryTag): CompoundBinaryTag {
        binaryTagMap.putAll(compoundBinaryTag.getValue())
        return this
    }

    fun putByte(key: String, value: Byte): CompoundBinaryTag {
        putBinaryTag(key, ByteBinaryTag(value))
        return this
    }

    fun putByte(key: String, value: Int): CompoundBinaryTag {
        putBinaryTag(key, ByteBinaryTag(value.toByte()))
        return this
    }

    fun getByte(key: String): Byte {
        return getBinaryTag(key)?.getValue() as Byte
    }

    fun putShort(key: String, value: Short): CompoundBinaryTag {
        putBinaryTag(key, ShortBinaryTag(value))
        return this
    }

    fun getShort(key: String): Short {
        return getBinaryTag(key)?.getValue() as Short
    }

    fun putInt(key: String, value: Int): CompoundBinaryTag {
        putBinaryTag(key, IntegerBinaryTag(value))
        return this
    }

    fun getInt(key: String): Int {
        return getBinaryTag(key)?.getValue() as Int
    }

    fun putLong(key: String, value: Long): CompoundBinaryTag {
        putBinaryTag(key, LongBinaryTag(value))
        return this
    }

    fun getLong(key: String): Long {
        return getBinaryTag(key)?.getValue() as Long
    }

    fun putDouble(key: String, value: Double): CompoundBinaryTag {
        putBinaryTag(key, DoubleBinaryTag(value))
        return this
    }

    fun getDouble(key: String): Double {
        return getBinaryTag(key)?.getValue() as Double
    }

    fun putByteArray(key: String, value: ByteArray): CompoundBinaryTag {
        putBinaryTag(key, ByteArrayBinaryTag(value))
        return this
    }

    fun getByteArray(key: String): ByteArray {
        return getBinaryTag(key)?.getValue() as ByteArray
    }

    fun putString(key: String, value: String): CompoundBinaryTag {
        putBinaryTag(key, StringBinaryTag(value))
        return this
    }

    fun getString(key: String): String {
        return getBinaryTag(key)?.getValue() as String
    }

    fun putIntArray(key: String, value: IntArray): CompoundBinaryTag {
        putBinaryTag(key, IntegerArrayBinaryTag(value))
        return this
    }

    fun getIntArray(key: String): IntArray {
        return getBinaryTag(key)?.getValue() as IntArray
    }

    fun putLongArray(key: String, value: LongArray): CompoundBinaryTag {
        putBinaryTag(key, LongArrayBinaryTag(value))
        return this
    }

    fun getLongArray(key: String): LongArray {
        return getBinaryTag(key)?.getValue() as LongArray
    }

    fun putBoolean(key: String, value: Boolean): CompoundBinaryTag {
        putBinaryTag(key, BooleanBinaryTag(value))
        return this
    }

    fun getBoolean(key: String): Boolean {
        return getBinaryTag(key)?.getValue() as Boolean
    }

    fun putFloat(key: String, value: Float): CompoundBinaryTag {
        putBinaryTag(key, FloatBinaryTag(value))
        return this
    }

    fun getFloat(key: String): Float {
        return getBinaryTag(key)?.getValue() as Float
    }

    fun containsKey(key: String?): Boolean {
        return binaryTagMap.containsKey(key)
    }

    fun isEmpty() = binaryTagMap.isEmpty()

    fun <T : BinaryTag<*>> computeIfAbsent(key: String, mappingFunction: Function<in String, out T>): T {
        return binaryTagMap.computeIfAbsent(key, mappingFunction) as T
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val tag = o as CompoundBinaryTag
        return binaryTagMap == tag.binaryTagMap
    }

    override fun hashCode(): Int {
        return Objects.hash(binaryTagMap)
    }

    override fun toString(): String {
        return "CompoundBinaryTag{" +
                "binaryTagMap=" + binaryTagMap +
                '}'
    }

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("CompoundBinaryTag{").append(System.lineSeparator())
        for ((key, value) in binaryTagMap) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append(key).append(": ")
            if (value is PrettyString) {
                stringBuilder.append((value as PrettyString).toPrettyString(deep + 1))
            } else {
                stringBuilder.append(value.getValue())
            }
            stringBuilder.append(System.lineSeparator())
        }
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }


    companion object {
        val EMPTY = CompoundBinaryTag()
    }
}