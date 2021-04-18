package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTagType;

public class ZstdLongArrayBinaryTag extends LongArrayBinaryTag {

    public ZstdLongArrayBinaryTag(long[] longs) {
        super(longs);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_LONG_ARRAY;
    }
}
