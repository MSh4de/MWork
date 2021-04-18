package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class ZstdIntegerArrayBinaryTagAdaptor extends IntegerArrayBinaryTagAdaptor{

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        return ((IntegerArrayBinaryTag) super.serialize(binaryTagMarshal, type, o)).toZstd();
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        return super.deserialize(binaryTagMarshal, type, binaryTag);
    }
}
