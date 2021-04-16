package eu.mshade.mwork.nametag.layer.primitive;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagAdaptor;
import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.entity.IntegerBinaryTag;

import java.lang.reflect.Type;

public class NameTagIntegerLayer implements NameTagAdaptor<Integer> {
    
    @Override
    public Integer deserialize(NameTagDriver nameTagDriver, Type type, BinaryTag<?> tag) {
        IntegerBinaryTag intTag = (IntegerBinaryTag) tag;
        return intTag.getValue();
    }

    @Override
    public BinaryTag<?> serialize(NameTagDriver nameTagDriver, Integer integer) {
        return new IntegerBinaryTag(integer);
    }
}
