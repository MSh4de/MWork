package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

public class EndBinaryTag implements BinaryTag<Integer> {

    public final static EndBinaryTag TAG = new EndBinaryTag();

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.END;
    }

    @Override
    public Integer getValue() {
        return 0;
    }


}
