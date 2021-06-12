package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTagType;

public class ShadeLongArrayBinaryTag extends LongArrayBinaryTag{

    public ShadeLongArrayBinaryTag(long[] longs) {
        super(longs);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.SHADE_LONG_ARRAY;
    }
}
