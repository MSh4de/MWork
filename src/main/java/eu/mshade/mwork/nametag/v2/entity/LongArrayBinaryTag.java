package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class LongArrayBinaryTag implements BinaryTag<Long[]> {

    @Override
    public BinaryTagType getType() {
        return null;
    }

    @Override
    public Long[] getValue() {
        return new Long[0];
    }
}
