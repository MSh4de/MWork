package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

public class ZstdListBinaryTag<T extends BinaryTag<?>> extends ListBinaryTag<T> {

    public ZstdListBinaryTag(Class<T> aClass) {
        super(aClass);
    }

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.ZSTD_LIST;
    }
}
