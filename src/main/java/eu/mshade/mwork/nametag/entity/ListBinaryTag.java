package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

import java.util.ArrayList;
import java.util.List;

public class ListBinaryTag<T extends BinaryTag<?>>  implements BinaryTag<List<T>>   {

    private final List<T> list = new ArrayList<>();
    private final Class<T> tClass;

    public ListBinaryTag(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.LIST;
    }


    public void add(T t){
        list.add(t);
    }

    @Override
    public List<T> getValue() {
        return list;
    }

    public Class<T> getElementType() {
        return tClass;
    }
}
