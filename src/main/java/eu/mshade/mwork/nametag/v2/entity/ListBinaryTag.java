package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

import java.util.List;

public class ListBinaryTag implements BinaryTag<List<BinaryTag<?>>> {

    @Override
    public BinaryTagType getType() {
        return null;
    }

    @Override
    public List<BinaryTag<?>> getValue() {
        return null;
    }
}
