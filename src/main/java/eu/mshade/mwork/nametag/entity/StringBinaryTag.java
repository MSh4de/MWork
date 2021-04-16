package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

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
