package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
