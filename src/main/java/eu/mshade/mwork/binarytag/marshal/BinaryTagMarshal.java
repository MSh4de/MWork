package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public interface BinaryTagMarshal {

    CompoundBinaryTag marshal(Object o);

    <T> T unMarshal(Class<T> aClass, CompoundBinaryTag compoundBinaryTag);

    void registerAdaptor(Class<?> aClass, BinaryTagAdaptor<?> binaryTagAdaptor);

    BinaryTagType getBinaryTagTypeByClass(Class<?> aClass);

    BinaryTagAdaptor<Object> getBinaryTagAdaptor(Class<?> aClass);

    BinaryTagAdaptor<Object> getBinaryTagAdaptorOf(Field field) throws Exception;

    String getNameOf(Field field);

    BinaryTagType getBinaryTagTypOf(Field field);

    Unsafe getUnsafe();
}
