package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.NameTagLayer;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;

public class NameTagStringLayer extends NameTagLayer<String> {

    @Override
    public String deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag tag) {
        StringBinaryTag str = (StringBinaryTag) tag;
        return str.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, String o) {
        return StringBinaryTag.of(o);
    }
}
