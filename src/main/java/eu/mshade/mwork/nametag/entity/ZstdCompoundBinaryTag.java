package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTagType;

public class ZstdCompoundBinaryTag extends CompoundBinaryTag{

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_COMPOUND;
    }
}
