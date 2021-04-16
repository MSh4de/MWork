package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.entity.ShortBinaryTag;

import java.lang.reflect.Type;


public class NameTagShortLayer implements NameTagAdaptor<Short> {

    @Override
    public Short deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag<?> tag) {
        ShortBinaryTag shortTag = (ShortBinaryTag) tag;
        return shortTag.getValue();
    }

    @Override
    public BinaryTag<?> serialize(NameTagDriver nameTagDriver, Short aShort) {
        return new ShortBinaryTag(aShort);
    }
}
