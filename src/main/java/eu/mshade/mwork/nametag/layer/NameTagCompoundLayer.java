package eu.mshade.mwork.nametag.layer;


import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.entity.CompoundBinaryTag;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class NameTagCompoundLayer implements NameTagAdaptor<Object> {

    @Override
    public BinaryTag<?> serialize(NameTagDriver nameTagDriver, Object o) {
        CompoundBinaryTag compoundTag = new CompoundBinaryTag();
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                nameTagDriver.getNameTagAdaptor(field.getType()).ifPresent(nameTagAdaptor -> {
                    compoundTag.putBinaryTag(field.getName(), nameTagAdaptor.serialize(nameTagDriver, field.get(o)));
                }).ifNotPresent(unused -> compoundTag.putBinaryTag(field.getName(), nameTagDriver.getNameTagLayer(field.getType()).serialize(nameTagDriver, field.get(o))));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return compoundTag;
    }

    @Override
    public Object deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag binaryTag) {
        try {
            Class<?> aClass = (Class<?>) type;
            CompoundBinaryTag tag = (CompoundBinaryTag) binaryTag;
            Constructor<?> constructor = aClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object o = constructor.newInstance();
            for (Field field : aClass.getDeclaredFields()) {
                field.setAccessible(true);
                nameTagDriver.getNameTagAdaptor(field.getType())
                        .exception(Throwable::printStackTrace)
                        .ifPresent(nameTagAdaptor -> {
                    field.set(o, nameTagAdaptor.deserialize(nameTagDriver, field.getGenericType(), tag.getBinaryTag(field.getName())));
                }).ifNotPresent(unused -> {
                     field.set(o, nameTagDriver.getNameTagLayer(field.getType()).deserialize(nameTagDriver, field.getGenericType(), tag.getBinaryTag(field.getName())));
                });
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
