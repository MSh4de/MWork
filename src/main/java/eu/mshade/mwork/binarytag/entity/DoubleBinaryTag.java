package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleBinaryTag that = (DoubleBinaryTag) o;
        return Double.compare(that.aDouble, aDouble) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aDouble);
    }

    @Override
    public String toString() {
        return "DoubleBinaryTag{" +
                "aDouble=" + aDouble +
                '}';
    }
}
