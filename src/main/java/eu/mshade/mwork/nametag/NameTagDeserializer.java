package eu.mshade.mwork.nametag;


import net.kyori.adventure.nbt.BinaryTag;

public interface NameTagDeserializer<T> {

    T deserialize(NameTagDriver nameTagDriver, Class<?> aClass, BinaryTag binaryTag);

}
