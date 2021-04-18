package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;
import eu.mshade.mwork.nametag.ZstdBinaryTag;

import java.util.Arrays;

public class LongArrayBinaryTag implements BinaryTag<long[]>, ZstdBinaryTag<ZstdLongArrayBinaryTag> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongArrayBinaryTag that = (LongArrayBinaryTag) o;
        return Arrays.equals(longs, that.longs);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(longs);
    }

    @Override
    public String toString() {
        return "LongArrayBinaryTag{" +
                "longs=" + Arrays.toString(longs) +
                '}';
    }

    @Override
    public ZstdLongArrayBinaryTag toZstd() {
        return new ZstdLongArrayBinaryTag(longs);
    }
}
