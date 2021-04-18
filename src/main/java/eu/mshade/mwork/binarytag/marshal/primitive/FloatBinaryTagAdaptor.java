package eu.mshade.mwork.binarytag.marshal.primitive;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.FloatBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagAdaptor;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class FloatBinaryTagAdaptor implements BinaryTagAdaptor<Object> {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception{
        return new FloatBinaryTag((Float) o);
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        return binaryTag.getValue();
    }
}
