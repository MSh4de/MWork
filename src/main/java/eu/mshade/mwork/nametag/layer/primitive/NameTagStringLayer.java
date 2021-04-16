package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.entity.StringBinaryTag;

import java.lang.reflect.Type;

public class NameTagStringLayer implements NameTagAdaptor<String> {

    @Override
    public String deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag<?> tag) {
        StringBinaryTag str = (StringBinaryTag) tag;
        return str.getValue();
    }

    @Override
    public BinaryTag<?> serialize(NameTagDriver nameTagDriver, String o) {
        return new StringBinaryTag(o);
    }
}
