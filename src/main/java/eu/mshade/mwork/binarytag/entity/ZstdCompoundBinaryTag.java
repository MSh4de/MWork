package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

import java.util.Map;

public class ZstdCompoundBinaryTag extends CompoundBinaryTag {

    public ZstdCompoundBinaryTag(Map<String, BinaryTag<?>> binaryTagMap) {
        super(binaryTagMap);
    }

    public ZstdCompoundBinaryTag() {
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_COMPOUND;
    }
}
