package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "ByteBinaryTag{" +
                "aByte=" + aByte +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteBinaryTag that = (ByteBinaryTag) o;
        return aByte == that.aByte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aByte);
    }
}
