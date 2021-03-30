package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.NameTagLayer;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.LongBinaryTag;


public class NameTagLongLayer extends NameTagLayer<Long> {

    @Override
    public Long deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag tag) {
        LongBinaryTag longTag = (LongBinaryTag) tag;
        return longTag.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Long aLong) {
        return LongBinaryTag.of(aLong);
    }
}
