package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTagType;

public class ShadeByteArrayBinaryTag extends ByteArrayBinaryTag {

    public ShadeByteArrayBinaryTag(byte[] bytes) {
        super(bytes);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.SHADE_BYTE_ARRAY;
    }


}
