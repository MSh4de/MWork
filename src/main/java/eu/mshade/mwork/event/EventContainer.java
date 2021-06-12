package eu.mshade.mwork.event;

import java.util.HashMap;
import java.util.Map;

public class EventContainer {

    public static EventContainer EMPTY = new EventContainer();
    public Map<String, Object> map = new HashMap<>();

    public static EventContainer of(){
        return new EventContainer();
    }

    public <T> T getContainer(Class<T> aClass){
        return aClass.cast(this.map.get(aClass.getSimpleName()));
    }

    public <T> T getContainer(String key, Class<T> aClass){
        return aClass.cast(this.map.get(key));
    }

    public EventContainer putContainer(Object value){
        map.put(value.getClass().getSimpleName(), value);
        return this;
    }

    public EventContainer putContainer(String key, Object value){
        this.map.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "EventContainer{" +
                "map=" + map +
                '}';
    }


}
