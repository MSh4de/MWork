package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class CompoundBinaryTagMarshalBuffer implements BinaryTagMarshalBuffer<Object> {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        CompoundBinaryTag compoundTag = new CompoundBinaryTag();
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = binaryTagMarshal.getNameOf(field);
            compoundTag.putBinaryTag(name, binaryTagMarshal.getBinaryTagAdaptorOf(field).serialize(binaryTagMarshal, field.getGenericType(), field.get(o)));
        }
        return compoundTag;
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        CompoundBinaryTag compoundBinaryTag = (CompoundBinaryTag) binaryTag;
        Class<?> aClass = (Class<?>) type;
        Object o = binaryTagMarshal.getUnsafe().allocateInstance(aClass);
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            String name = binaryTagMarshal.getNameOf(field);
            field.set(o, binaryTagMarshal.getBinaryTagAdaptorOf(field).deserialize(binaryTagMarshal, field.getGenericType(), compoundBinaryTag.getBinaryTag(name)));
        }
        return o;
    }

}
