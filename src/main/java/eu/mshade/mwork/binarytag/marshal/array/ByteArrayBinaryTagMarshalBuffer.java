package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.ByteArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshalBuffer;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class ByteArrayBinaryTagMarshalBuffer implements BinaryTagMarshalBuffer<Object> {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o, ParameterContainer parameterContainer) throws Exception {
        return new ByteArrayBinaryTag((byte[]) o);
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag, ParameterContainer parameterContainer) throws Exception {
        return binaryTag.getValue();
    }
}