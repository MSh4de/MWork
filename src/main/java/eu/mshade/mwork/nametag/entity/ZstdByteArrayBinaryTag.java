package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ZstdByteArrayBinaryTag extends ByteArrayBinaryTag {

    public ZstdByteArrayBinaryTag(byte[] bytes) {
        super(bytes);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_BYTE_ARRAY;
    }
}
