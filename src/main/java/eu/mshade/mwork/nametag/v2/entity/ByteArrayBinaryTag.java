package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ByteArrayBinaryTag implements BinaryTag<byte[]> {

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
}
