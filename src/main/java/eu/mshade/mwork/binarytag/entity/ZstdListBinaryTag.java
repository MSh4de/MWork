package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.List;

public class ZstdListBinaryTag extends ListBinaryTag {

    public ZstdListBinaryTag(BinaryTagType binaryTagType) {
        super(binaryTagType);
    }

    public ZstdListBinaryTag(List<BinaryTag<?>> binaryTagList, BinaryTagType elementType) {
        super(binaryTagList, elementType);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_LIST;
    }
}
