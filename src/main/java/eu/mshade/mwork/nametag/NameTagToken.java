package eu.mshade.mwork.nametag;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class NameTagToken<T> {

    private Type type;

    public NameTagToken() {
        this.type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public ParameterizedType getTypeToken() {
        return (ParameterizedType) type;
    }


}
