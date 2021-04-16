package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class StringBinaryTag implements BinaryTag<String> {

    private final String string;

    public StringBinaryTag(String string) {
        this.string = string;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.STRING;
    }

    @Override
    public String getValue() {
        return string;
    }
}
