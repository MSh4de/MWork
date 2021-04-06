package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;

public class NameTagIntegerLayer implements NameTagAdaptor<Integer> {
    
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
