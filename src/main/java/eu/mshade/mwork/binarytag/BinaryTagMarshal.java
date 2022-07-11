package eu.mshade.mwork.binarytag;

public interface BinaryTagMarshal<T> {

    BinaryTag<?> serialize(BinaryTagDriver binaryTagDriver, T t) throws Exception;

    T deserialize(BinaryTagDriver binaryTagDriver, BinaryTag<?> binaryTag) throws Exception;
}
