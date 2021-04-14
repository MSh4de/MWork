package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

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
