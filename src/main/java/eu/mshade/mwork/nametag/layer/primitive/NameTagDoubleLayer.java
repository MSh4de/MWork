package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;

import java.lang.reflect.Type;


public class NameTagDoubleLayer implements NameTagAdaptor<Double> {

    @Override
    public Double deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag tag) {
        DoubleBinaryTag doubleT = (DoubleBinaryTag) tag;
        return doubleT.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Double aDouble) {
        return DoubleBinaryTag.of(aDouble);
    }
}
