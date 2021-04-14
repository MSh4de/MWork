package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

import java.util.ArrayList;
import java.util.List;

public class ListBinaryTag<T extends BinaryTag<?>> implements BinaryTag<List<T>> {

    private final List<T> list = new ArrayList<>();
    private final Class<T> tClass;

    public ListBinaryTag(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.LIST;
    }

    @Override
    public List<T> getValue() {
        return list;
    }

    public Class<T> getElementType() {
        return tClass;
    }
}
