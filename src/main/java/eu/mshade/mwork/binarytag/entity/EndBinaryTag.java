package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
