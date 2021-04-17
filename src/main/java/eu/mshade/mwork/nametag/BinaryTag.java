package eu.mshade.mwork.nametag;

public interface BinaryTag<T> {

    BinaryTagType getType();

    T getValue();

}
