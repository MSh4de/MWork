package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

import java.util.LinkedHashMap;
import java.util.Map;

public class CompoundBinaryTag implements BinaryTag<Map<String, BinaryTag<?>>> {

    private final Map<String, BinaryTag<?>> binaryTagMap = new LinkedHashMap<>();

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.COMPOUND;
    }

    @Override
    public Map<String, BinaryTag<?>> getValue() {
        return binaryTagMap;
    }
}
