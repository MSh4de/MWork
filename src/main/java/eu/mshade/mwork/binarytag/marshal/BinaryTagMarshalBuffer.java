package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.BinaryTag;

import java.lang.reflect.Type;

public interface BinaryTagMarshalBuffer<T> {

    BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, T t, ParameterContainer parameterContainer) throws Exception;

    T deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag, ParameterContainer parameterContainer) throws Exception;
}
