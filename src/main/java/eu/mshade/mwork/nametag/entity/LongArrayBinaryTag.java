package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

public class LongArrayBinaryTag implements BinaryTag<long[]> {

    private final long[] longs;

    public LongArrayBinaryTag(long[] longs) {
        this.longs = longs;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.LONG_ARRAY;
    }

    @Override
    public long[] getValue() {
        return longs;
    }
}
