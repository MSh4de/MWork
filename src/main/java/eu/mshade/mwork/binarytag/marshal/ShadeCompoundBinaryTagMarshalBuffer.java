package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.lang.reflect.Type;

public class ShadeCompoundBinaryTagMarshalBuffer extends CompoundBinaryTagMarshalBuffer {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o, ParameterContainer container) throws Exception {
        return ((CompoundBinaryTag) super.serialize(binaryTagMarshal, type, o, container)).toShade();
    }

}