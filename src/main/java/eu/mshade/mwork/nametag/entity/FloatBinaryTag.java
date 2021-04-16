package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

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
}
