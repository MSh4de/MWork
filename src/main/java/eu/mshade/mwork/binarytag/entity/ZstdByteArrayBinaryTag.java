package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTagType;

public class ZstdByteArrayBinaryTag extends ByteArrayBinaryTag {

    public ZstdByteArrayBinaryTag(byte[] bytes) {
        super(bytes);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_BYTE_ARRAY;
    }


}
