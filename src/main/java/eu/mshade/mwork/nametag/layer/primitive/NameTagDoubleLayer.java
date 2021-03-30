package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.NameTagLayer;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;


public class NameTagDoubleLayer extends NameTagLayer<Double> {

    @Override
    public Double deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag tag) {
        DoubleBinaryTag doubleT = (DoubleBinaryTag) tag;
        return doubleT.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Double aDouble) {
        return DoubleBinaryTag.of(aDouble);
    }
}
