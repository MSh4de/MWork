package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.Objects;

public class IntegerBinaryTag implements BinaryTag<Integer> {

    private final int anInt;

    public IntegerBinaryTag(int anInt) {
        this.anInt = anInt;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.INTEGER;
    }

    @Override
    public Integer getValue() {
        return anInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerBinaryTag that = (IntegerBinaryTag) o;
        return anInt == that.anInt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anInt);
    }

    @Override
    public String toString() {
        return "IntegerBinaryTag{" +
                "anInt=" + anInt +
                '}';
    }
}
