package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;

import java.util.Arrays;

public class IntegerArrayBinaryTag implements BinaryTag<int[]>, ZstdBinaryTag<ZstdIntegerArrayBinaryTag> {

    private final int[] ints;

    public IntegerArrayBinaryTag(int[] ints) {
        this.ints = ints;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.INTEGER_ARRAY;
    }

    @Override
    public int[] getValue() {
        return ints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerArrayBinaryTag that = (IntegerArrayBinaryTag) o;
        return Arrays.equals(ints, that.ints);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ints);
    }

    @Override
    public String toString() {
        return "IntegerArrayBinaryTag{" +
                "ints=" + Arrays.toString(ints) +
                '}';
    }

    @Override
    public ZstdIntegerArrayBinaryTag toZstd() {
        return new ZstdIntegerArrayBinaryTag(ints);
    }
}
