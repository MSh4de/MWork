package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayBinaryTag<T extends BinaryTag<?>> implements BinaryTag<T[]> {

    private final T[] array;
    private final BinaryTagType elementType;

    public ArrayBinaryTag(BinaryTagType elementType, T[] array) {
        this.array = array;
        this.elementType = elementType;
    }

    public ArrayBinaryTag(BinaryTagType elementType, int size) {
        this.array = (T[]) Array.newInstance(elementType.getClazz(), size);
        this.elementType = elementType;
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ARRAY;
    }

    @Override
    public T[] getValue() {
        return array;
    }

    public BinaryTagType getElementType() {
        return elementType;
    }
}
