package eu.mshade.mwork;

import java.util.HashMap;
import java.util.Map;

public class ParameterContainer {
    public static ParameterContainer EMPTY = new ParameterContainer();
    public Map<String, Object> map = new HashMap<>();

    public static ParameterContainer of(){
        return new ParameterContainer();
    }

    public <T> T getContainer(Class<T> aClass){
        return aClass.cast(this.map.get(aClass.getSimpleName()));
    }

    public <T> T getContainer(String key, Class<T> aClass){
        return aClass.cast(this.map.get(key));
    }

    public ParameterContainer putContainer(Object value){
        map.put(value.getClass().getSimpleName(), value);
        return this;
    }

    public ParameterContainer putContainer(String key, Object value){
        this.map.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "ParameterContainer{" +
                "map=" + map +
                '}';
    }
}
