package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.List;

public interface BinaryTagMarshal {

    BinaryTag<?> marshal(Object o);

    BinaryTag<?> marshal(Object o, Class<?> aClass);

    BinaryTag<?> marshal(Object o, ParameterContainer parameterContainer);

    BinaryTag<?> marshal(Object o, Class<?> aClass, ParameterContainer parameterContainer);

    <T> T unMarshal(BinaryTag<?> binaryTag, Class<T> aClass);

    <T> T unMarshal(BinaryTag<?> binaryTag, Class<T> aClass, ParameterContainer parameterContainer);

    BinaryTagMarshalBufferModule registerAdaptor(Class<?> aClass, BinaryTagMarshalBuffer<?> binaryTagMarshalBuffer);

    BinaryTagMarshalBuffer<Object> getBinaryTagAdaptorOf(Class<?> aClass) throws Exception;

    BinaryTagMarshalBuffer<Object> getBinaryTagAdaptorOf(Field field) throws Exception;

    String getNameOf(Field field);

    BinaryTagType getBinaryTagTypOf(Class<?> aClass);

    Unsafe getUnsafe();
}
