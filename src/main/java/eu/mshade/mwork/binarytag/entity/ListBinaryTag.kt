package eu.mshade.mwork.binarytag.entity

import eu.mshade.mwork.PrettyString
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.BinaryTagKey

open class ListBinaryTag(binaryTagKey: BinaryTagKey = BinaryTagType.LIST, val elementType: BinaryTagKey, list: MutableList<BinaryTag<*>> = mutableListOf()) :
    BinaryTag<MutableList<BinaryTag<*>>>(binaryTagKey, list), PrettyString {


    constructor(elementType: BinaryTagKey) : this(BinaryTagType.LIST, elementType, mutableListOf())
    constructor(binaryTagKey: BinaryTagKey, elementType: BinaryTagKey) : this(binaryTagKey, elementType, mutableListOf())


    fun add(element: BinaryTag<*>): ListBinaryTag {
        value.add(element)
        return this
    }

    fun remove(element: BinaryTag<*>): ListBinaryTag {
        value.remove(element)
        return this
    }

    fun remove(index: Int): ListBinaryTag {
        value.removeAt(index)
        return this
    }

    fun get(index: Int): BinaryTag<*> {
        return value[index]
    }

    fun contains(element: BinaryTag<*>): Boolean {
        return value.contains(element)
    }

    fun size(): Int {
        return value.size
    }

    fun isEmpty(): Boolean {
        return value.isEmpty()
    }

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("ListBinaryTag(elementType=${elementType.getName()}){")
        stringBuilder.append(System.lineSeparator())
        this.value.take(5).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("-").append(' ')
            if (it is PrettyString) {
                stringBuilder.append(it.toPrettyString(deep + 3))
            } else {
                stringBuilder.append(it.value)
            }
            stringBuilder.append(System.lineSeparator())
        }
        if (this.value.size > 5) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("+ ${this.value.size - 5} more")
            stringBuilder.append(System.lineSeparator())
        }
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }


}
