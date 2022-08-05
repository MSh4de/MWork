package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.LinkedHashMap;
import java.util.Map;

public class ZstdCompoundBinaryTag extends CompoundBinaryTag {

    public ZstdCompoundBinaryTag(Map<String, BinaryTag<?>> binaryTagMap) {
        super(binaryTagMap);
    }

    public ZstdCompoundBinaryTag() {
        super();
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_COMPOUND;
    }
}
