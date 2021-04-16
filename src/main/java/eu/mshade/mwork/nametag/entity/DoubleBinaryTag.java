package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
