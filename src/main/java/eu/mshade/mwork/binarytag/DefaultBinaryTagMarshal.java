package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.marshal.*;
import eu.mshade.mwork.binarytag.marshal.array.*;
import eu.mshade.mwork.binarytag.marshal.primitive.*;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.*;

public class DefaultBinaryTagMarshal implements BinaryTagMarshal {

    private final static Map<Class<?>, BinaryTagType> CLASS_BINARY_TAG_TYPE = new HashMap<>();
    private static final Map<Class<?>, BinaryTagAdaptor<Object>> BINARY_TAG_ADAPTOR_MAP = new HashMap<>();
    private static Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.BYTE.getClazz(), new ByteBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.SHORT.getClazz(), new ShortBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.INTEGER.getClazz(), new IntegerBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.LONG.getClazz(), new LongBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.FLOAT.getClazz(), new FloatBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.DOUBLE.getClazz(), new DoubleBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.BYTE_ARRAY.getClazz(), new ByteArrayBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.STRING.getClazz(), new StringBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.LIST.getClazz(), new ListBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.COMPOUND.getClazz(), new CompoundBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.INTEGER_ARRAY.getClazz(), new IntegerArrayBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.LONG_ARRAY.getClazz(), new LongArrayBinaryTagAdaptor());

        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.BOOLEAN.getClazz(), new BooleanBinaryTagAdaptor());

        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.SHADE_BYTE_ARRAY.getClazz(), new ShadeByteArrayBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.SHADE_LIST.getClazz(), new ShadeListBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.SHADE_COMPOUND.getClazz(), new ShadeCompoundBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.SHADE_INTEGER_ARRAY.getClazz(), new ShadeIntegerArrayBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.SHADE_LONG_ARRAY.getClazz(), new ShadeLongArrayBinaryTagAdaptor());

        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.ZSTD_BYTE_ARRAY.getClazz(), new ZstdByteArrayBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.ZSTD_LIST.getClazz(), new ZstdListBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.ZSTD_COMPOUND.getClazz(), new ZstdCompoundBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.ZSTD_INTEGER_ARRAY.getClazz(), new ZstdIntegerArrayBinaryTagAdaptor());
        BINARY_TAG_ADAPTOR_MAP.put(BinaryTagType.ZSTD_LONG_ARRAY.getClazz(), new ZstdLongArrayBinaryTagAdaptor());
    }

    @Override
    public CompoundBinaryTag marshal(Object o) {
        try {
           return (CompoundBinaryTag) ((BinaryTagAdaptor)getBinaryTagAdaptorOf(o.getClass())).serialize(this, o.getClass(), o);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public <T> T unMarshal(Class<T> aClass, CompoundBinaryTag compoundBinaryTag) {
        try {
            return (T) getBinaryTagAdaptorOf(aClass).deserialize(this, aClass, compoundBinaryTag);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerAdaptor(Class<?> aClass, BinaryTagAdaptor<?> binaryTagAdaptor) {
        BINARY_TAG_ADAPTOR_MAP.putIfAbsent(aClass, (BinaryTagAdaptor<Object>) binaryTagAdaptor);
    }


    private BinaryTagAdaptor<Object> getBinaryTagAdaptorByClass(Class<?> aClass) {
        if (CLASS_BINARY_TAG_TYPE.containsKey(aClass)) {
            return BINARY_TAG_ADAPTOR_MAP.get(CLASS_BINARY_TAG_TYPE.get(aClass).getClazz());
        }else if (BINARY_TAG_ADAPTOR_MAP.containsKey(aClass)){
            return BINARY_TAG_ADAPTOR_MAP.get(aClass);
        }
        return BINARY_TAG_ADAPTOR_MAP.get(BinaryTagType.COMPOUND.getClazz());
    }

    @Override
    public BinaryTagAdaptor<Object> getBinaryTagAdaptorOf(Class<?> aClass) throws Exception {
        BinaryTagProperty binaryTagProperty = aClass.getDeclaredAnnotation(BinaryTagProperty.class);
        if (binaryTagProperty != null){
            return this.getBinaryTagAdaptorByClass(binaryTagProperty.value().getClazz());
        }
        return this.getBinaryTagAdaptorByClass(aClass);
    }

    @Override
    public BinaryTagAdaptor<Object> getBinaryTagAdaptorOf(Field field) throws Exception {
        if (field.getDeclaredAnnotation(BinaryTagAdapt.class) != null) {
            return (BinaryTagAdaptor) this.getUnsafe().allocateInstance(field.getDeclaredAnnotation(BinaryTagAdapt.class).value());
        }
        BinaryTagProperty binaryTagProperty = field.getDeclaredAnnotation(BinaryTagProperty.class);
        if (binaryTagProperty != null){
            return this.getBinaryTagAdaptorByClass(binaryTagProperty.value().getClazz());
        }

        return this.getBinaryTagAdaptorByClass(field.getType());
    }

    @Override
    public String getNameOf(Field field) {
        return (field.getDeclaredAnnotation(BinaryTagName.class) != null? field.getDeclaredAnnotation(BinaryTagName.class).value(): field.getName());
    }

    @Override
    public BinaryTagType getBinaryTagTypOf(Class<?> aClass) {
        return (aClass.getDeclaredAnnotation(BinaryTagProperty.class) != null? aClass.getDeclaredAnnotation(BinaryTagProperty.class).value(): CLASS_BINARY_TAG_TYPE.getOrDefault(aClass, BinaryTagType.COMPOUND));
    }

    @Override
    public Unsafe getUnsafe() {
        return UNSAFE;
    }


}
