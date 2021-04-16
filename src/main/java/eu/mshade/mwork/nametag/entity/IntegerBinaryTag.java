package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
}
