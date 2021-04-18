package eu.mshade.mwork.binarytag;

public interface BinaryTag<T> {

    BinaryTagType getType();

    T getValue();

}
