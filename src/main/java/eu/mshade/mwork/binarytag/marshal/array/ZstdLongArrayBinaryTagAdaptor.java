package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class ZstdLongArrayBinaryTagAdaptor extends LongArrayBinaryTagAdaptor{

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        return ((LongArrayBinaryTag) super.serialize(binaryTagMarshal, type, o)).toZstd();
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        return super.deserialize(binaryTagMarshal, type, binaryTag);
    }
}
