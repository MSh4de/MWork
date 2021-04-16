package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ZstdLongArrayBinaryTag extends LongArrayBinaryTag {

    public ZstdLongArrayBinaryTag(long[] longs) {
        super(longs);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_LONG_ARRAY;
    }
}
