package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.MWork;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.*;

public class ServiceContext {

    private final Method method;
    private final Map<String, Class<?>> params = new LinkedHashMap<>();

    public ServiceContext(Method method) {
        this.method = method;
        for (Parameter parameter : method.getParameters()) {
            if (!parameter.isAnnotationPresent(TradeAction.class)) return;
            params.put(parameter.getAnnotation(TradeAction.class).value(), parameter.getType());
        }
    }

    public Object invokeMethode(Session session, Service service, JSONObject jsonObject) throws Exception{
        List<Object> objects = new ArrayList<>();
        for (Map.Entry<String, Class<?>> entry : params.entrySet()) {
            if (entry.getValue().isAssignableFrom(Session.class)) {
                objects.add(session);
            }else if (entry.getValue().isEnum()){
                objects.add(Enum.valueOf(((Class) entry.getValue()), jsonObject.getString(entry.getKey())));
            }else if (MWork.get().isPrimitive(entry.getValue())) {
                objects.add(jsonObject.get(entry.getKey()));
            } else objects.add(MWork.get().deserialize(jsonObject.get(entry.getKey()).toString(), entry.getValue()));
        }
        return method.invoke(service, objects.toArray(new Object[0]));
    }

    public int getParamsSize(){
        return this.params.size();
    }


    public boolean valid(Set<String> params) {
        if (params.size() == this.params.size()) {
            for (String param : params) if (!this.params.containsKey(param)) return false;
            return true;
        }
        return false;
    }

}
