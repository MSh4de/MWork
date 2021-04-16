package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class LongBinaryTag implements BinaryTag<Long> {

    private final long aLong;

    public LongBinaryTag(long aLong) {
        this.aLong = aLong;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.LONG;
    }

    @Override
    public Long getValue() {
        return aLong;
    }
}
