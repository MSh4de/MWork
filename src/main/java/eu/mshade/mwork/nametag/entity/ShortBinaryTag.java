package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ShortBinaryTag implements BinaryTag<Short> {

    private final short aShort;

    public ShortBinaryTag(short aShort) {
        this.aShort = aShort;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.SHORT;
    }

    @Override
    public Short getValue() {
        return aShort;
    }

}
