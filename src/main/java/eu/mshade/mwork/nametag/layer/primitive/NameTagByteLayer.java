package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.ByteBinaryTag;

import java.lang.reflect.Type;


public class NameTagByteLayer implements NameTagAdaptor<Byte> {

    @Override
    public Byte deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag tag) {
        ByteBinaryTag byteT = (ByteBinaryTag) tag;
        return byteT.value();
    }

    @Override
    public BinaryTag serialize(NameTagDriver nameTagDriver, Byte aByte) {
        return ByteBinaryTag.of(aByte);
    }
}
