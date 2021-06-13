package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public interface BinaryTagMarshal {

    CompoundBinaryTag marshal(Object o);

    <T> T unMarshal(CompoundBinaryTag compoundBinaryTag, Class<T> aClass);

    void registerAdaptor(Class<?> aClass, BinaryTagMarshalBuffer<?> binaryTagMarshalBuffer);

    BinaryTagMarshalBuffer<Object> getBinaryTagAdaptorOf(Class<?> aClass) throws Exception;

    BinaryTagMarshalBuffer<Object> getBinaryTagAdaptorOf(Field field) throws Exception;

    String getNameOf(Field field);

    BinaryTagType getBinaryTagTypOf(Class<?> aClass);

    Unsafe getUnsafe();
}
