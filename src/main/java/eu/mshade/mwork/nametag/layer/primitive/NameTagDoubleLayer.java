package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.entity.DoubleBinaryTag;

import java.lang.reflect.Type;


public class NameTagDoubleLayer implements NameTagAdaptor<Double> {

    @Override
    public Double deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag<?> tag) {
        DoubleBinaryTag doubleT = (DoubleBinaryTag) tag;
        return doubleT.getValue();
    }

    @Override
    public BinaryTag<?> serialize(NameTagDriver nameTagDriver, Double aDouble) {
        return new DoubleBinaryTag(aDouble);
    }
}
