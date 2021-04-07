package eu.mshade.mwork.nametag;

import net.kyori.adventure.nbt.BinaryTag;

import java.lang.reflect.Type;

public interface NameTagAdaptor<T> {

    BinaryTag serialize(NameTagDriver nameTagDriver, T t);

    T deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag binaryTag);
}
