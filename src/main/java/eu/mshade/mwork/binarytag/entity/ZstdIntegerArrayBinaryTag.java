package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTagType;

public class ZstdIntegerArrayBinaryTag extends IntegerArrayBinaryTag{

    public ZstdIntegerArrayBinaryTag(int[] ints) {
        super(ints);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_INTEGER_ARRAY;
    }
}
