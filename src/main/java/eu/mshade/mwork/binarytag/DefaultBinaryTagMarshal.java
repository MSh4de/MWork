package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.MWork;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.binarytag.marshal.*;
import eu.mshade.mwork.binarytag.marshal.array.*;
import eu.mshade.mwork.binarytag.marshal.primitive.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultBinaryTagMarshal implements BinaryTagMarshal {

    private final static Map<Class<?>, BinaryTagType> CLASS_BINARY_TAG_TYPE = new HashMap<>();
    private final Map<Class<?>, BinaryTagMarshalBuffer<Object>> classBinaryTagMarshalBuffer = new HashMap<>();
    private static Logger LOGGER = LoggerFactory.getLogger(BinaryTagMarshal.class);


    public DefaultBinaryTagMarshal() {
        classBinaryTagMarshalBuffer.put(BinaryTagType.BYTE.getClazz(), new ByteBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.SHORT.getClazz(), new ShortBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.INTEGER.getClazz(), new IntegerBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.LONG.getClazz(), new LongBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.FLOAT.getClazz(), new FloatBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.DOUBLE.getClazz(), new DoubleBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.BYTE_ARRAY.getClazz(), new ByteArrayBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.STRING.getClazz(), new StringBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.LIST.getClazz(), new ListBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.COMPOUND.getClazz(), new CompoundBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.INTEGER_ARRAY.getClazz(), new IntegerArrayBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.LONG_ARRAY.getClazz(), new LongArrayBinaryTagMarshalBuffer());

        classBinaryTagMarshalBuffer.put(BinaryTagType.BOOLEAN.getClazz(), new BooleanBinaryTagMarshalBuffer());

        classBinaryTagMarshalBuffer.put(BinaryTagType.SHADE_BYTE_ARRAY.getClazz(), new ShadeByteArrayBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.SHADE_LIST.getClazz(), new ShadeListBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.SHADE_COMPOUND.getClazz(), new ShadeCompoundBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.SHADE_INTEGER_ARRAY.getClazz(), new ShadeIntegerArrayBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.SHADE_LONG_ARRAY.getClazz(), new ShadeLongArrayBinaryTagMarshalBuffer());

        classBinaryTagMarshalBuffer.put(BinaryTagType.ZSTD_BYTE_ARRAY.getClazz(), new ZstdByteArrayBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.ZSTD_LIST.getClazz(), new ZstdListBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.ZSTD_COMPOUND.getClazz(), new ZstdCompoundBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.ZSTD_INTEGER_ARRAY.getClazz(), new ZstdIntegerArrayBinaryTagMarshalBuffer());
        classBinaryTagMarshalBuffer.put(BinaryTagType.ZSTD_LONG_ARRAY.getClazz(), new ZstdLongArrayBinaryTagMarshalBuffer());
    }

    static {

        CLASS_BINARY_TAG_TYPE.put(byte.class, BinaryTagType.BYTE);
        CLASS_BINARY_TAG_TYPE.put(Byte.class, BinaryTagType.BYTE);
        CLASS_BINARY_TAG_TYPE.put(short.class, BinaryTagType.SHORT);
        CLASS_BINARY_TAG_TYPE.put(Short.class, BinaryTagType.SHORT);
        CLASS_BINARY_TAG_TYPE.put(int.class, BinaryTagType.INTEGER);
        CLASS_BINARY_TAG_TYPE.put(Integer.class, BinaryTagType.INTEGER);
        CLASS_BINARY_TAG_TYPE.put(long.class, BinaryTagType.LONG);
        CLASS_BINARY_TAG_TYPE.put(Long.class, BinaryTagType.LONG);
        CLASS_BINARY_TAG_TYPE.put(double.class, BinaryTagType.DOUBLE);
        CLASS_BINARY_TAG_TYPE.put(Double.class, BinaryTagType.DOUBLE);
        CLASS_BINARY_TAG_TYPE.put(byte[].class, BinaryTagType.BYTE_ARRAY);
        CLASS_BINARY_TAG_TYPE.put(Byte[].class, BinaryTagType.BYTE_ARRAY);
        CLASS_BINARY_TAG_TYPE.put(String.class, BinaryTagType.STRING);
        CLASS_BINARY_TAG_TYPE.put(int[].class, BinaryTagType.INTEGER_ARRAY);
        CLASS_BINARY_TAG_TYPE.put(Integer[].class, BinaryTagType.INTEGER_ARRAY);
        CLASS_BINARY_TAG_TYPE.put(Long[].class, BinaryTagType.LONG_ARRAY);
        CLASS_BINARY_TAG_TYPE.put(long[].class, BinaryTagType.LONG_ARRAY);
        CLASS_BINARY_TAG_TYPE.put(boolean.class, BinaryTagType.BOOLEAN);
        CLASS_BINARY_TAG_TYPE.put(Boolean.class, BinaryTagType.BOOLEAN);
        CLASS_BINARY_TAG_TYPE.put(ArrayList.class, BinaryTagType.LIST);
        CLASS_BINARY_TAG_TYPE.put(List.class, BinaryTagType.LIST);
    }

    @Override
    public BinaryTag<?> marshal(Object o) {
        return marshal(o, ParameterContainer.EMPTY);
    }

    @Override
    public BinaryTag<?> marshal(Object o, Class<?> aClass) {
        return marshal(o, aClass, ParameterContainer.EMPTY);
    }

    @Override
    public BinaryTag<?> marshal(Object o, ParameterContainer container) {
        return marshal(o, o.getClass(), container);
    }

    @Override
    public BinaryTag<?> marshal(Object o, Class<?> aClass, ParameterContainer parameterContainer) {
        try {
            BinaryTagMarshalBuffer binaryTagMarshalBuffer = getBinaryTagAdaptorOf(aClass);
            return binaryTagMarshalBuffer.serialize(this, o.getClass(), o, parameterContainer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T unMarshal(BinaryTag<?> binaryTag, Class<T> aClass) {
        try {
            return unMarshal(binaryTag, aClass, ParameterContainer.EMPTY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T unMarshal(BinaryTag<?> binaryTag, Class<T> aClass, ParameterContainer container) {
        try {
            return (T) getBinaryTagAdaptorOf(aClass).deserialize(this, aClass, binaryTag, container);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BinaryTagMarshalBufferModule registerAdaptor(Class<?> aClass, BinaryTagMarshalBuffer<?> binaryTagMarshalBuffer) {
        classBinaryTagMarshalBuffer.putIfAbsent(aClass, (BinaryTagMarshalBuffer<Object>) binaryTagMarshalBuffer);
        return new DefaultBinaryTagMarshalBufferModule(binaryTagMarshalBuffer, this);
    }

    private BinaryTagMarshalBuffer<Object> getBinaryTagAdaptorByClass(Class<?> aClass) {
        if (CLASS_BINARY_TAG_TYPE.containsKey(aClass)) {
            return classBinaryTagMarshalBuffer.get(CLASS_BINARY_TAG_TYPE.get(aClass).getClazz());
        } else if (classBinaryTagMarshalBuffer.containsKey(aClass)) {
            return classBinaryTagMarshalBuffer.get(aClass);
        }
        return classBinaryTagMarshalBuffer.get(BinaryTagType.COMPOUND.getClazz());
    }

    @Override
    public BinaryTagMarshalBuffer<Object> getBinaryTagAdaptorOf(Class<?> aClass) throws Exception {
        BinaryTagProperty binaryTagProperty = aClass.getDeclaredAnnotation(BinaryTagProperty.class);
        if (binaryTagProperty != null) {
            return this.getBinaryTagAdaptorByClass(binaryTagProperty.value().getClazz());
        }
        return this.getBinaryTagAdaptorByClass(aClass);
    }

    @Override
    public BinaryTagMarshalBuffer<Object> getBinaryTagAdaptorOf(Field field) throws Exception {
        if (field.getDeclaredAnnotation(BinaryTagMarshalType.class) != null) {
            return (BinaryTagMarshalBuffer) this.getUnsafe().allocateInstance(field.getDeclaredAnnotation(BinaryTagMarshalType.class).value());
        }
        BinaryTagProperty binaryTagProperty = field.getDeclaredAnnotation(BinaryTagProperty.class);
        if (binaryTagProperty != null) {
            return this.getBinaryTagAdaptorByClass(binaryTagProperty.value().getClazz());
        }

        return this.getBinaryTagAdaptorByClass(field.getType());
    }

    @Override
    public String getNameOf(Field field) {
        return (field.getDeclaredAnnotation(BinaryTagName.class) != null ? field.getDeclaredAnnotation(BinaryTagName.class).value() : field.getName());
    }

    @Override
    public BinaryTagType getBinaryTagTypOf(Class<?> aClass) {
        return (aClass.getDeclaredAnnotation(BinaryTagProperty.class) != null ? aClass.getDeclaredAnnotation(BinaryTagProperty.class).value() : CLASS_BINARY_TAG_TYPE.getOrDefault(aClass, BinaryTagType.COMPOUND));
    }

    @Override
    public Unsafe getUnsafe() {
        return MWork.get().getUnsafe();
    }


}
