package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.NameTagLayer;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.ByteBinaryTag;


public class NameTagByteLayer extends NameTagLayer<Byte> {

    @Override
    public Byte deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag tag) {
        ByteBinaryTag byteT = (ByteBinaryTag) tag;
        return byteT.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Byte aByte) {
        return ByteBinaryTag.of(aByte);
    }
}
