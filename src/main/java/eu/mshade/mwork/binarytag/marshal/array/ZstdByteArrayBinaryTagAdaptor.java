package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.ByteArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class ZstdByteArrayBinaryTagAdaptor extends ByteArrayBinaryTagAdaptor {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        return ((ByteArrayBinaryTag) super.serialize(binaryTagMarshal, type, o)).toZstd();
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        return super.deserialize(binaryTagMarshal, type, binaryTag);
    }
}
