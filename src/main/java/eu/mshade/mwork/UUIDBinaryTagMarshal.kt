package eu.mshade.mwork

import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.BinaryTagDriver
import eu.mshade.mwork.binarytag.BinaryTagMarshal
import eu.mshade.mwork.binarytag.StringBinaryTag
import java.util.*

class UUIDBinaryTagMarshal : BinaryTagMarshal<UUID> {

    override fun serialize(binaryTagDriver: BinaryTagDriver, uuid: UUID): BinaryTag<*> {
        return StringBinaryTag(uuid.toString())
    }

    override fun deserialize(binaryTagDriver: BinaryTagDriver, binaryTag: BinaryTag<*>): UUID {
        return UUID.fromString(binaryTag.value as String?)
    }
}