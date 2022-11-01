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
        getValue().add(element)
        return this
    }

    fun remove(element: BinaryTag<*>): ListBinaryTag {
        getValue().remove(element)
        return this
    }

    fun remove(index: Int): ListBinaryTag {
        getValue().removeAt(index)
        return this
    }

    fun get(index: Int): BinaryTag<*> {
        return getValue()[index]
    }

    fun contains(element: BinaryTag<*>): Boolean {
        return getValue().contains(element)
    }

    fun size(): Int {
        return getValue().size
    }

    override fun toPrettyString(deep: Int): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("ListBinaryTag(elementType=${elementType.getName()}){")
        stringBuilder.append(System.lineSeparator())
        this.getValue().take(5).forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("-").append(' ')
            if (it is PrettyString) {
                stringBuilder.append(it.toPrettyString(deep + 3))
            } else {
                stringBuilder.append(it.getValue())
            }
            stringBuilder.append(System.lineSeparator())
        }
        if (this.getValue().size > 5) {
            stringBuilder.append(" ".repeat(deep + 1))
            stringBuilder.append("+ ${this.getValue().size - 5} more")
            stringBuilder.append(System.lineSeparator())
        }
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }


}
