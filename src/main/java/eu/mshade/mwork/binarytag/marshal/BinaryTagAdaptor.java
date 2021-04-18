package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTag;

import java.lang.reflect.Type;

public interface BinaryTagAdaptor<T> {

    BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, T t) throws Exception;

    T deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception;

}
