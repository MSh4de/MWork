package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

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
