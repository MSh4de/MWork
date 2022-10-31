package eu.mshade.mwork.binarytag.entity

import eu.mshade.mwork.PrettyString
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.BinaryTagTypeKey

open class ListBinaryTag(binaryTagTypeKey: BinaryTagTypeKey = BinaryTagType.LIST, val elementType: BinaryTagTypeKey, list: MutableList<BinaryTag<*>> = mutableListOf()) :
    BinaryTag<MutableList<BinaryTag<*>>>(binaryTagTypeKey, list), PrettyString {


    constructor(elementType: BinaryTagTypeKey) : this(BinaryTagType.LIST, elementType, mutableListOf())
    constructor(binaryTagTypeKey: BinaryTagTypeKey, elementType: BinaryTagTypeKey) : this(binaryTagTypeKey, elementType, mutableListOf())


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
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("ListBinaryTag(elementType=$elementType, value=[")
        stringBuilder.append(System.lineSeparator())
        this.getValue().forEach {
            stringBuilder.append(" ".repeat(deep + 1))
            if (it is PrettyString) {
                stringBuilder.append(it.toPrettyString(deep + 1))
            } else {
                stringBuilder.append(it.toString())
            }
            stringBuilder.append(System.lineSeparator())
        }
        stringBuilder.append(" ".repeat(deep))
        stringBuilder.append("])")
        return stringBuilder.toString()
    }


}
