package eu.mshadeproduction.mwork.dispatcher;

import java.util.HashMap;
import java.util.Map;

public class DefaultDispatcherContainer implements DispatcherContainer{

    public Map<String, Object> map = new HashMap<>();

    @Override
    public <T> T getContainer(Class<T> aClass){
        return aClass.cast(this.map.get(aClass.getSimpleName()));
    }

    @Override
    public <T> T getContainer(String key, Class<T> aClass){
        return aClass.cast(this.map.get(key));
    }

    public void setContainer(Object value){
        map.put(value.getClass().getSimpleName(), value);
    }

    public void setContainer(String key, Object value){
        this.map.put(key, value);
    }

}
