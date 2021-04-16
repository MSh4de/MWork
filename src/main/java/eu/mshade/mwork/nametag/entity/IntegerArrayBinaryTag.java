package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class IntegerArrayBinaryTag implements BinaryTag<int[]> {

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
}
