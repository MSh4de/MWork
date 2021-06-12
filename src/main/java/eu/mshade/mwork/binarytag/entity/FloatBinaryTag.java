package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.Objects;

public class FloatBinaryTag implements BinaryTag<Float> {

    private final Float aFloat;

    public FloatBinaryTag(Float aFloat) {
        this.aFloat = aFloat;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.FLOAT;
    }

    @Override
    public Float getValue() {
        return aFloat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatBinaryTag that = (FloatBinaryTag) o;
        return Objects.equals(aFloat, that.aFloat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aFloat);
    }

    @Override
    public String toString() {
        return "FloatBinaryTag{" +
                "aFloat=" + aFloat +
                '}';
    }
}
