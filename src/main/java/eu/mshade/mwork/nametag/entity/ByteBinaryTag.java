package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
