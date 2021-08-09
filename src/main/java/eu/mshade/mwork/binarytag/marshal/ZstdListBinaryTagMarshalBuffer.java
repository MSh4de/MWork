package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.lang.reflect.Type;

public class ZstdListBinaryTagMarshalBuffer extends ListBinaryTagMarshalBuffer {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o, ParameterContainer parameterContainer) throws Exception {
        return ((ListBinaryTag) super.serialize(binaryTagMarshal, type, o, parameterContainer)).toZstd();
    }
}
