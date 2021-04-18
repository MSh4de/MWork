package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.lang.reflect.Type;

public class ZstdCompoundBinaryTagAdaptor implements BinaryTagAdaptor<Object>{

    @Override
    public BinaryTag<?> serialize(BinaryTagMarshal binaryTagMarshal, Type type, Object o) throws Exception {
        return ((CompoundBinaryTag) binaryTagMarshal.getBinaryTagAdaptor(BinaryTagType.COMPOUND.getClazz()).serialize(binaryTagMarshal, type, o)).toZstd();
    }

    @Override
    public Object deserialize(BinaryTagMarshal binaryTagMarshal, Type type, BinaryTag<?> binaryTag) throws Exception {
        return binaryTagMarshal.getBinaryTagAdaptor(BinaryTagType.COMPOUND.getClazz()).deserialize(binaryTagMarshal, type, binaryTag);
    }
}
