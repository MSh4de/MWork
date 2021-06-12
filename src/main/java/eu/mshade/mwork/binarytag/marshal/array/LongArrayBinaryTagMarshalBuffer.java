package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshalBuffer;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class LongArrayBinaryTagMarshalBuffer implements BinaryTagMarshalBuffer<Object> {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception{
        return new LongArrayBinaryTag((long[]) o);
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception{
        return binaryTag.getValue();
    }
}
