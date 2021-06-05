package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.ShadeBinaryTag;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListBinaryTag extends ArrayList<BinaryTag<?>> implements BinaryTag<Iterable<BinaryTag<?>>>, ZstdBinaryTag<ZstdListBinaryTag>, ShadeBinaryTag<ShadeListBinaryTag> {

    private final BinaryTagType elementType;

    public ListBinaryTag(BinaryTagType elementType) {
        this.elementType = elementType;
    }

    public ListBinaryTag(List<BinaryTag<?>> binaryTagList, BinaryTagType elementType) {
        super(binaryTagList);
        this.elementType = elementType;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.LIST;
    }

    @Override
    public Iterable<BinaryTag<?>> getValue() {
        return this;
    }

    public BinaryTagType getElementType() {
        return elementType;
    }

    @Override
    public ZstdListBinaryTag toZstd() {
        return new ZstdListBinaryTag(this, elementType);
    }

    @Override
    public ShadeListBinaryTag toShade() {
        return new ShadeListBinaryTag(this, elementType);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ListBinaryTag that = (ListBinaryTag) o;
        return elementType == that.elementType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), elementType);
    }
}
