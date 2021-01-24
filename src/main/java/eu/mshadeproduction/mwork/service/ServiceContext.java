package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.MWork;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class ServiceContext {

    private final Method method;
    private final Map<String, Class<?>> params = new LinkedHashMap<>();

    public ServiceContext(Method method) {
        this.method = method;
        for (Parameter parameter : method.getParameters()) {
            if (!parameter.isAnnotationPresent(TradeAction.class)) return;
            params.put(parameter.getAnnotation(TradeAction.class).name(), parameter.getType());
        }
    }

    public Object invokeMethode(ServiceCreator serviceCreator, Service service, JSONObject jsonObject) throws Exception{
        List<Object> objects = new ArrayList<>();
        for (Map.Entry<String, Class<?>> entry : params.entrySet()) {
            if (MWork.get().isPrimitive(entry.getValue())) {
                objects.add(jsonObject.get(entry.getKey()));
                //objects.add(serviceCreator.getTypeWrap().get(entry.getValue()).get(jsonObject.getString(entry.getKey())));
            } else objects.add(MWork.get().deserialize(jsonObject.get(entry.getKey()).toString(), entry.getValue()));
        }
        return method.invoke(service, objects.toArray(new Object[0]));
    }


    public boolean valid(Set<String> params) {
        if (params.size() == this.params.size()) {
            for (String param : params) if (!this.params.containsKey(param)) return false;
            return true;
        }
        return false;
    }

}
