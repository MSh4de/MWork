package eu.mshade.mwork;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagMarshal;

import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDBinaryTagMarshal implements BinaryTagMarshal<UUID> {

    @Override
    public BinaryTag<?> serialize(BinaryTagDriver binaryTagDriver, UUID uuid) throws Exception {
        return new StringBinaryTag(uuid.toString());
    }

    @Override
    public UUID deserialize(BinaryTagDriver binaryTagDriver, BinaryTag<?> binaryTag) throws Exception {
        return UUID.fromString((String) binaryTag.getValue());
    }
}
