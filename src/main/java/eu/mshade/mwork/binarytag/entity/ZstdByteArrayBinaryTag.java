package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTagType;

public class ZstdByteArrayBinaryTag extends ByteArrayBinaryTag {

    public ZstdByteArrayBinaryTag(byte[] bytes) {
        super(bytes);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_BYTE_ARRAY;
    }


}
