package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.entity.LongBinaryTag;

import java.lang.reflect.Type;


public class NameTagLongLayer implements NameTagAdaptor<Long> {

    @Override
    public Long deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag<?> tag) {
        LongBinaryTag longTag = (LongBinaryTag) tag;
        return longTag.getValue();
    }

    @Override
    public BinaryTag<?> serialize(NameTagDriver nameTagDriver, Long aLong) {
        return new LongBinaryTag(aLong);
    }
}
