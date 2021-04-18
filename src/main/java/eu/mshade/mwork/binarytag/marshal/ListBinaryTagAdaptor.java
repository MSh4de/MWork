package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListBinaryTagAdaptor implements BinaryTagAdaptor<Object> {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        List<Object> objects = (List<Object>) o;
        Class<?> model = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println(model);
        BinaryTagType binaryTagType = binaryTagMarshal.getBinaryTagTypeByClass(model);
        ListBinaryTag listBinaryTag =  new ListBinaryTag(binaryTagType);
        for (Object object : objects) {
            listBinaryTag.add(binaryTagMarshal.marshal(object));
        }
        return listBinaryTag;
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        List<Object> objects = new ArrayList<>();
        Class<?> model = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        ListBinaryTag listBinaryTag = (ListBinaryTag) binaryTag;
        for (BinaryTag<?> tag : listBinaryTag) {
            objects.add(binaryTagMarshal.getBinaryTagAdaptor(listBinaryTag.getElementType().getClazz()).deserialize(binaryTagMarshal, model, tag));
        }
        return objects;
    }
}
