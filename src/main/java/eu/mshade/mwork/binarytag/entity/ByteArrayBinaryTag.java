package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;

import java.util.Arrays;

public class ByteArrayBinaryTag implements BinaryTag<byte[]>, ZstdBinaryTag<ZstdByteArrayBinaryTag> {

    private final byte[] bytes;

    public ByteArrayBinaryTag(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.BYTE_ARRAY;
    }

    @Override
    public byte[] getValue() {
        return bytes;
    }

    @Override
    public String toString() {
        return "ByteArrayBinaryTag{" +
                "bytes=" + Arrays.toString(bytes) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteArrayBinaryTag that = (ByteArrayBinaryTag) o;
        return Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public ZstdByteArrayBinaryTag toZstd() {
        return new ZstdByteArrayBinaryTag(bytes);
    }
}
