package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class ZstdIntegerArrayBinaryTagMarshalBuffer extends IntegerArrayBinaryTagMarshalBuffer {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        return ((IntegerArrayBinaryTag) super.serialize(binaryTagMarshal, type, o)).toZstd();
    }

}
