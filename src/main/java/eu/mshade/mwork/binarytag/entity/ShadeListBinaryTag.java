package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.List;

public class ShadeListBinaryTag extends ListBinaryTag {

    public ShadeListBinaryTag(BinaryTagType elementType) {
        super(elementType);
    }

    public ShadeListBinaryTag(List<BinaryTag<?>> binaryTagList, BinaryTagType elementType) {
        super(binaryTagList, elementType);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.SHADE_LIST;
    }

}
