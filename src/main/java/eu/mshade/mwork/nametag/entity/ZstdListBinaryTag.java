package eu.mshade.mwork.nametag.entity;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;

public class ZstdListBinaryTag<T extends BinaryTag<?>> extends ListBinaryTag<T> {

    public ZstdListBinaryTag(Class<T> aClass) {
        super(aClass);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_LIST;
    }
}
