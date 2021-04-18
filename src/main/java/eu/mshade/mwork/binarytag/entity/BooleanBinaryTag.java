package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

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
}
