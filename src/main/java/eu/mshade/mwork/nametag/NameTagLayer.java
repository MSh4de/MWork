package eu.mshade.mwork.nametag;


import eu.mshade.mwork.nametag.layer.NameTagCompoundLayer;
import eu.mshade.mwork.nametag.layer.primitive.*;

import java.util.HashMap;
import java.util.Map;

public abstract class NameTagLayer<T> implements NameTagSerializer<T>, NameTagDeserializer<T>  {

    private static final Map<NameTagType, NameTagLayer<?>> nameTagLayers = new HashMap<>();

    static {
        nameTagLayers.put(NameTagType.OBJECT, new NameTagCompoundLayer());
        nameTagLayers.put(NameTagType.SHORT, new NameTagShortLayer());
        nameTagLayers.put(NameTagType.BYTE, new NameTagByteLayer());
        nameTagLayers.put(NameTagType.INTEGER, new NameTagIntegerLayer());
        nameTagLayers.put(NameTagType.LONG, new NameTagLongLayer());
        nameTagLayers.put(NameTagType.FLOAT, new NameTagFloatLayer());
        nameTagLayers.put(NameTagType.DOUBLE, new NameTagDoubleLayer());
        nameTagLayers.put(NameTagType.STRING, new NameTagStringLayer());
    }


    public NameTagLayer<Object> getNameTagLayer(Class<?> aClass){
        return (NameTagLayer<Object>) nameTagLayers.get(NameTagType.getNameTagType(aClass));
    }
}
