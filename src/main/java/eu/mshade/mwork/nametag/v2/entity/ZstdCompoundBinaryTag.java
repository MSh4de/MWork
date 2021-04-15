package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ZstCompoundBinaryTag extends CompoundBinaryTag{

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_COMPOUND;
    }
}
