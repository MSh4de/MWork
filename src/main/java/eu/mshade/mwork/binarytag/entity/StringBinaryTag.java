package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringBinaryTag that = (StringBinaryTag) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

    @Override
    public String toString() {
        return "StringBinaryTag{" +
                "string='" + string + '\'' +
                '}';
    }
}
