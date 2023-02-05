package eu.mshade.mwork.binarytag

interface BinaryTagMarshal<T> {

    fun serialize(binaryTagDriver: BinaryTagDriver, t: T): BinaryTag<*>

    fun deserialize(binaryTagDriver: BinaryTagDriver, binaryTag: BinaryTag<*>): T
}