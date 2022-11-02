package eu.mshade.mwork;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.BinaryTagMarshal;
import eu.mshade.mwork.binarytag.StringBinaryTag;

import java.util.UUID;

public class UUIDBinaryTagMarshal implements BinaryTagMarshal<UUID> {

    @Override
    public BinaryTag<?> serialize(BinaryTagDriver binaryTagDriver, UUID uuid) {
        return new StringBinaryTag(uuid.toString());
    }

    @Override
    public UUID deserialize(BinaryTagDriver binaryTagDriver, BinaryTag<?> binaryTag) {
        return UUID.fromString((String) binaryTag.getValue());
    }
}
