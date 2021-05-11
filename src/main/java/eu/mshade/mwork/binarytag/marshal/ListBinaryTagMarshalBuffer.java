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
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        Class<?> model = (Class<?>) typeArgument;
        BinaryTagType binaryTagType = binaryTagMarshal.getBinaryTagTypOf(model);
        ListBinaryTag listBinaryTag =  new ListBinaryTag(binaryTagType);
        BinaryTagAdaptor<Object> binaryTagAdaptor = binaryTagMarshal.getBinaryTagAdaptorOf(model);
        for (Object object : objects) {
            listBinaryTag.add(binaryTagAdaptor.serialize(binaryTagMarshal, typeArgument, object));
        }
        return listBinaryTag;
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        List<Object> objects = new ArrayList<>();
        Class<?> model = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        ListBinaryTag listBinaryTag = (ListBinaryTag) binaryTag;
        BinaryTagAdaptor<Object> binaryTagAdaptor = binaryTagMarshal.getBinaryTagAdaptorOf(listBinaryTag.getElementType().getClazz());
        for (BinaryTag<?> tag : listBinaryTag) {
            objects.add(binaryTagAdaptor.deserialize(binaryTagMarshal, model, tag));
        }
        return objects;
    }
}
