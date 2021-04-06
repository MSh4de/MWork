package eu.mshade.mwork.nametag;

import net.kyori.adventure.nbt.BinaryTag;

public interface NameTagAdaptor<T> {

    BinaryTag serialize(NameTagDriver nameTagDriver, T t);

    T deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag binaryTag);
}
