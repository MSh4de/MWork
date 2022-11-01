package eu.mshade.mwork

interface PrettyString {

    fun toPrettyString(deep: Int): String

    fun toPrettyString() = toPrettyString(0)

}