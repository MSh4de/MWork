package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTagType;

public class ZstdIntegerArrayBinaryTag extends IntegerArrayBinaryTag{

    public ZstdIntegerArrayBinaryTag(int[] ints) {
        super(ints);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_INTEGER_ARRAY;
    }
}
