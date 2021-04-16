package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
