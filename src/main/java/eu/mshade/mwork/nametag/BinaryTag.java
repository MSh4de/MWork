package eu.mshade.mwork.nametag.v2;

public interface BinaryTag<T> {

    BinaryTagType getType();

    T getValue();

}
