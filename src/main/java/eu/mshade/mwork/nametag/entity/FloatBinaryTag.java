package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
