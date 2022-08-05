package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShadeCompoundBinaryTag extends CompoundBinaryTag {

    public ShadeCompoundBinaryTag(Map<String, BinaryTag<?>> binaryTagMap) {
        super(binaryTagMap);
    }

    public ShadeCompoundBinaryTag() {
        super();
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.SHADE_COMPOUND;
    }
}
