package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.NameTagLayer;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.ShortBinaryTag;


public class NameTagShortLayer extends NameTagLayer<Short> {

    @Override
    public Short deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag tag) {
        ShortBinaryTag shortTag = (ShortBinaryTag) tag;
        return shortTag.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Short aShort) {
        return ShortBinaryTag.of(aShort);
    }
}
