package eu.mshade.mwork.nametag;


import eu.mshade.mwork.MOptional;
import eu.mshade.mwork.nametag.layer.NameTagCompoundLayer;
import eu.mshade.mwork.nametag.layer.list.NameTagListLayer;
import eu.mshade.mwork.nametag.layer.primitive.*;
import net.kyori.adventure.nbt.BinaryTag;

import java.util.HashMap;
import java.util.Map;

public class NameTagDriver {

    private final Map<Class<?>, NameTagAdaptor<Object>> nameTagAdaptors = new HashMap<>();

    private static final Map<NameTagType, NameTagAdaptor<?>> nameTagLayers = new HashMap<>();

    static {
        nameTagLayers.put(NameTagType.OBJECT, new NameTagCompoundLayer());
        nameTagLayers.put(NameTagType.SHORT, new NameTagShortLayer());
        nameTagLayers.put(NameTagType.BYTE, new NameTagByteLayer());
        nameTagLayers.put(NameTagType.INTEGER, new NameTagIntegerLayer());
        nameTagLayers.put(NameTagType.LONG, new NameTagLongLayer());
        nameTagLayers.put(NameTagType.FLOAT, new NameTagFloatLayer());
        nameTagLayers.put(NameTagType.DOUBLE, new NameTagDoubleLayer());
        nameTagLayers.put(NameTagType.STRING, new NameTagStringLayer());
        nameTagLayers.put(NameTagType.LIST, new NameTagListLayer());
    }



    public BinaryTag serialize(Object o){
        return getNameTagLayer(o.getClass()).serialize(this, o);
    }

    public <T> T deserialize(BinaryTag namedTag, Class<T> t){
        return t.cast(getNameTagLayer(t).deserialize(this, t, namedTag));
    }

    public <T> T deserialize(BinaryTag namedTag, NameTagToken<T> tagToken){
        return null;
    }

    public <T> void registerNameTagAdaptor(Class<T> aClass, NameTagAdaptor<T> nameTagAdaptor){
        nameTagAdaptors.putIfAbsent(aClass, (NameTagAdaptor<Object>) nameTagAdaptor);
    }

    public MOptional<NameTagAdaptor<Object>> getNameTagAdaptor(Class<?> aClass){
        return MOptional.ofNullable(nameTagAdaptors.get(aClass));
    }


    public NameTagAdaptor<Object> getNameTagLayer(Class<?> aClass){
        return (NameTagAdaptor<Object>) nameTagLayers.get(NameTagType.getNameTagType(aClass));
    }

}
