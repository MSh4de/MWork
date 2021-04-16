package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ZstdIntegerArrayBinaryTag extends IntegerArrayBinaryTag{

    public ZstdIntegerArrayBinaryTag(int[] ints) {
        super(ints);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_INTEGER_ARRAY;
    }
}
