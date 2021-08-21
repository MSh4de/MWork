package eu.mshade.mwork.binarytag.marshal.array;

import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.ByteArrayBinaryTag;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;

import java.lang.reflect.Type;

public class ShadeByteArrayBinaryTagMarshalBuffer extends ByteArrayBinaryTagMarshalBuffer {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o, ParameterContainer parameterContainer) throws Exception {
        return ((ByteArrayBinaryTag) super.serialize(binaryTagMarshal, type, o, parameterContainer)).toShade();
    }

}
