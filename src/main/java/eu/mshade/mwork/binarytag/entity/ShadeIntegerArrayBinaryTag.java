package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTagType;

public class ShadeIntegerArrayBinaryTag extends IntegerArrayBinaryTag{
    public ShadeIntegerArrayBinaryTag(int[] ints) {
        super(ints);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.SHADE_INTEGER_ARRAY;
    }
}
