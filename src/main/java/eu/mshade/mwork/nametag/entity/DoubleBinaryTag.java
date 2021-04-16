package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class DoubleBinaryTag implements BinaryTag<Double> {

    private final double aDouble;

    public DoubleBinaryTag(double aDouble) {
        this.aDouble = aDouble;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.DOUBLE;
    }

    @Override
    public Double getValue() {
        return aDouble;
    }
}
