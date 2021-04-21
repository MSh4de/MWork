package eu.mshade.mwork;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagAdaptor;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDBinaryTagAdaptor implements BinaryTagAdaptor<UUID> {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, UUID uuid) throws Exception {
        return new StringBinaryTag(uuid.toString());
    }

    @Override
    public UUID deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        return UUID.fromString((String) binaryTag.getValue());
    }
}
