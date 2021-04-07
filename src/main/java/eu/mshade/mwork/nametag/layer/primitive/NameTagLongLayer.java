package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.LongBinaryTag;

import java.lang.reflect.Type;


public class NameTagLongLayer implements NameTagAdaptor<Long> {

    @Override
    public Long deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag tag) {
        LongBinaryTag longTag = (LongBinaryTag) tag;
        return longTag.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Long aLong) {
        return LongBinaryTag.of(aLong);
    }
}
