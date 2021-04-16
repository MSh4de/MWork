package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTagType;

public class ZstdLongArrayBinaryTag extends LongArrayBinaryTag {

    public ZstdLongArrayBinaryTag(long[] longs) {
        super(longs);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_LONG_ARRAY;
    }
}
