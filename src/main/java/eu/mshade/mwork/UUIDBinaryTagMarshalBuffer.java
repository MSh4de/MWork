package eu.mshade.mwork;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshalBuffer;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDBinaryTagMarshalBuffer implements BinaryTagMarshalBuffer<UUID> {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, UUID uuid, ParameterContainer parameterContainer) throws Exception {
        return new StringBinaryTag(uuid.toString());
    }

    @Override
    public UUID deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag, ParameterContainer parameterContainer) throws Exception {
        return UUID.fromString((String) binaryTag.getValue());
    }
}
