package eu.mshade.mwork.nametag.layer.list;

import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameTagListLayer implements NameTagAdaptor<List<?>> {

    @Override
    public List<?> deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag binaryTag) {
        List<Object> list = new ArrayList<>();
        System.out.println(Arrays.toString(((ParameterizedType) type).getActualTypeArguments()));
        System.out.println(((ParameterizedType) type).getActualTypeArguments()[0]);
        Class<?> model = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        ListBinaryTag tags = (ListBinaryTag) binaryTag;
        try {
            for (BinaryTag tag : tags) {
                Constructor<?> constructor = model.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object o = constructor.newInstance();
                for (Field field : model.getDeclaredFields()) {
                    field.setAccessible(true);
                    nameTagDriver.getNameTagAdaptor(field.getType()).ifPresent(nameTagAdaptor -> {
                        field.set(o, nameTagAdaptor.deserialize(nameTagDriver, field.getGenericType(), get(field.getName(), tag)));
                    }).ifNotPresent(unused -> {
                        field.set(o, nameTagDriver.getNameTagLayer(field.getType()).deserialize(nameTagDriver, field.getGenericType(), get(field.getName(), tag)));
                    });
                }
                list.add(o);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, List<?> list) {
        ListBinaryTag.Builder<BinaryTag> builder = ListBinaryTag.builder();
        list.forEach(o -> builder.add(nameTagDriver.serialize(o)));
        return builder.build();
    }

    private BinaryTag get(String key, BinaryTag binaryTag){
        if (binaryTag instanceof CompoundBinaryTag) {
            return ((CompoundBinaryTag) binaryTag).get(key);
        }
        return binaryTag;
    }

}
