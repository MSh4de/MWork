package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class ZstdLongArrayBinaryTagMarshalBuffer extends LongArrayBinaryTagMarshalBuffer {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o, ParameterContainer container) throws Exception {
        return ((LongArrayBinaryTag) super.serialize(binaryTagMarshal, type, o, container)).toZstd();
    }

}
