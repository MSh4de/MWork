package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class EndBinaryTag implements BinaryTag<Void> {

    @Override
    public BinaryTagType getType() {
        return null;
    }

    @Override
    public Void getValue() {
        return null;
    }
}
