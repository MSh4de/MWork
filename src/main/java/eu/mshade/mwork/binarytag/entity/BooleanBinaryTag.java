package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.Objects;

public class BooleanBinaryTag implements BinaryTag<Boolean> {

    private final boolean aBoolean;

    public BooleanBinaryTag(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.BOOLEAN;
    }

    @Override
    public Boolean getValue() {
        return aBoolean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanBinaryTag that = (BooleanBinaryTag) o;
        return aBoolean == that.aBoolean;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aBoolean);
    }
}
