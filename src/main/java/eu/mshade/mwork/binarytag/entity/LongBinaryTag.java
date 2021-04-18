package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongBinaryTag that = (LongBinaryTag) o;
        return aLong == that.aLong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aLong);
    }

    @Override
    public String toString() {
        return "LongBinaryTag{" +
                "aLong=" + aLong +
                '}';
    }
}
