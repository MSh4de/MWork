package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortBinaryTag that = (ShortBinaryTag) o;
        return aShort == that.aShort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aShort);
    }

    @Override
    public String toString() {
        return "ShortBinaryTag{" +
                "aShort=" + aShort +
                '}';
    }
}
