package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.lang.reflect.Type;

public class ZstdListBinaryTagAdaptor extends ListBinaryTagAdaptor {

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        return ((ListBinaryTag) super.serialize(binaryTagMarshal, type, o)).toZstd();
    }
}
