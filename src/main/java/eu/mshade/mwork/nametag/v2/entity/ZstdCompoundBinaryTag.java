package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ZstdCompoundBinaryTag extends CompoundBinaryTag{

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_COMPOUND;
    }
}
