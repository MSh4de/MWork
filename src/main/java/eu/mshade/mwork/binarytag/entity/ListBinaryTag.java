package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListBinaryTag implements BinaryTag<Iterable<BinaryTag<?>>>, Iterable<BinaryTag<?>>, ZstdBinaryTag<ZstdListBinaryTag> {

    private List<BinaryTag<?>> binaryTagList = new ArrayList<>();
    private final BinaryTagType elementType;

    public ListBinaryTag(BinaryTagType elementType) {
        this.elementType = elementType;
    }

    public ListBinaryTag(List<BinaryTag<?>> binaryTagList, BinaryTagType elementType) {
        this.binaryTagList = binaryTagList;
        this.elementType = elementType;
    }

    public void add(BinaryTag<?> binaryTag){
        if (binaryTag.getType() == this.elementType) {
            this.binaryTagList.add(binaryTag);
        }
    }

    public int size(){
        return this.binaryTagList.size();
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.LIST;
    }

    @Override
    public Iterable<BinaryTag<?>> getValue() {
        return binaryTagList;
    }

    public BinaryTagType getElementType() {
        return elementType;
    }

    @Override
    public Iterator<BinaryTag<?>> iterator() {
        return binaryTagList.iterator();
    }


    @Override
    public ZstdListBinaryTag toZstd() {
        return new ZstdListBinaryTag(binaryTagList, elementType);
    }
}
