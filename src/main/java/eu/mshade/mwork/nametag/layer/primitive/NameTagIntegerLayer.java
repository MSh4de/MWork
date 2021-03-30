package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.NameTagLayer;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;

public class NameTagIntegerLayer extends NameTagLayer<Integer> {
    
    @Override
    public Integer deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag tag) {
        IntBinaryTag intTag = (IntBinaryTag) tag;
        return intTag.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Integer integer) {
        return IntBinaryTag.of(integer);
    }
}
