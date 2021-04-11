package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

import java.util.Map;

public class CompoundBinaryTag implements BinaryTag<Map<String, BinaryTag<?>>> {

    @Override
    public BinaryTagType getType() {
        return null;
    }

    @Override
    public Map<String, BinaryTag<?>> getValue() {
        return null;
    }
}
