package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ByteBinaryTag implements BinaryTag<Byte> {

    private final byte aByte;

    public ByteBinaryTag(byte aByte) {
        this.aByte = aByte;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.BYTE;
    }

    @Override
    public Byte getValue() {
        return null;
    }
}
