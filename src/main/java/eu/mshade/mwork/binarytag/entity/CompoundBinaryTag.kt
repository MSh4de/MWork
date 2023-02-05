package eu.mshade.mwork.binarytag.entity

import eu.mshade.mwork.PrettyString
import eu.mshade.mwork.binarytag.*
import java.util.*
import java.util.function.Function

open class CompoundBinaryTag(binaryTagKey: BinaryTagKey = BinaryTagType.COMPOUND, private val binaryTagMap: MutableMap<String, BinaryTag<*>> = mutableMapOf()) :
    BinaryTag<MutableMap<String, BinaryTag<*>>>(
        binaryTagKey, binaryTagMap
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
        binaryTagMap.putAll(compoundBinaryTag.value)
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

    fun putByte(key: String, value: Boolean): CompoundBinaryTag {
        putByte(key, if (value) 1 else 0)
        return this
    }

    fun getByte(key: String): Byte {
        return getBinaryTag(key)?.value as? Byte ?: 0
    }

    fun putShort(key: String, value: Short): CompoundBinaryTag {
        putBinaryTag(key, ShortBinaryTag(value))
        return this
    }

    fun getShort(key: String): Short {
        return getBinaryTag(key)?.value as? Short ?: 0
    }

    fun putInt(key: String, value: Int): CompoundBinaryTag {
        putBinaryTag(key, IntBinaryTag(value))
        return this
    }

    fun getInt(key: String): Int {
        return getBinaryTag(key)?.value as? Int ?: 0
    }

    fun putLong(key: String, value: Long): CompoundBinaryTag {
        putBinaryTag(key, LongBinaryTag(value))
        return this
    }

    fun getLong(key: String): Long {
        return getBinaryTag(key)?.value as? Long ?: 0
    }

    fun putDouble(key: String, value: Double): CompoundBinaryTag {
        putBinaryTag(key, DoubleBinaryTag(value))
        return this
    }

    fun getDouble(key: String): Double {
        return getBinaryTag(key)?.value as? Double ?: 0.0
    }

    fun putByteArray(key: String, value: ByteArray): CompoundBinaryTag {
        putBinaryTag(key, ByteArrayBinaryTag(value))
        return this
    }

    fun getByteArray(key: String): ByteArray {
        return getBinaryTag(key)?.value as? ByteArray ?: ByteArray(0)
    }

    fun putString(key: String, value: String): CompoundBinaryTag {
        putBinaryTag(key, StringBinaryTag(value))
        return this
    }

    fun getString(key: String): String? {
        return getBinaryTag(key)?.value as? String
    }

    fun putIntArray(key: String, value: IntArray): CompoundBinaryTag {
        putBinaryTag(key, IntArrayBinaryTag(value))
        return this
    }

    fun getIntArray(key: String): IntArray {
        return getBinaryTag(key)?.value as? IntArray ?: IntArray(0)
    }

    fun putLongArray(key: String, value: LongArray): CompoundBinaryTag {
        putBinaryTag(key, LongArrayBinaryTag(value))
        return this
    }

    fun getLongArray(key: String): LongArray {
        return getBinaryTag(key)?.value as? LongArray ?: LongArray(0)
    }

    fun putBoolean(key: String, value: Boolean): CompoundBinaryTag {
        putBinaryTag(key, BooleanBinaryTag(value))
        return this
    }

    fun getBoolean(key: String): Boolean {
        return getBinaryTag(key)?.value as? Boolean ?: false
    }

    fun putFloat(key: String, value: Float): CompoundBinaryTag {
        putBinaryTag(key, FloatBinaryTag(value))
        return this
    }

    fun getFloat(key: String): Float {
        return getBinaryTag(key)?.value as? Float ?: 0.0f
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
        stringBuilder.append("CompoundBinaryTag{").append(System.lineSeparator())
        for ((key, value) in binaryTagMap) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append(key).append("(${value.type.getName()})").append(": ")
            if (value is PrettyString) {
                stringBuilder.append(value.toPrettyString(deep + 1))
            } else {
                stringBuilder.append(value.value)
            }
            stringBuilder.append(System.lineSeparator())
        }
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }


    companion object {
        @JvmField val EMPTY = CompoundBinaryTag()
    }
}