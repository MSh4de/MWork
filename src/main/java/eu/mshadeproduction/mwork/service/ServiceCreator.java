package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.MWork;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.*;

public class ServiceCreator {

    private final Service service;
    private final Map<String, ServiceContext> serviceContext = new HashMap<>();
    private final Map<Class<?>, TypeWrap<?, String>> typeWrap = new HashMap<>();

    public ServiceCreator(Service service) {
        this.service = service;

        typeWrap.put(String.class, s -> s);
        typeWrap.put(int.class, Integer::parseInt);
        typeWrap.put(Integer.class, Integer::parseInt);
        typeWrap.put(boolean.class, Boolean::parseBoolean);
        typeWrap.put(Boolean.class, Boolean::parseBoolean);
        typeWrap.put(float.class, Float::parseFloat);
        typeWrap.put(Float.class, Float::parseFloat);
        typeWrap.put(char.class, s -> s.toCharArray()[0]);
        typeWrap.put(Character.class, s -> s.toCharArray()[0]);
        typeWrap.put(byte.class, Byte::parseByte);
        typeWrap.put(Byte.class, Byte::parseByte);
        typeWrap.put(long.class, Long::parseLong);
        typeWrap.put(Long.class, Long::parseLong);
        typeWrap.put(double.class, Double::parseDouble);
        typeWrap.put(Double.class, Double::parseDouble);

        for (Method method : service.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(TradeAction.class)) {
                serviceContext.put(method.getAnnotation(TradeAction.class).name(), new ServiceContext(method));
            }
        }
    }

    public void invokeService(TradeRequest tradeRequest, TradeResponse tradeResponse){
        Optional<ServiceContext> contextOptional = Optional.ofNullable(this.serviceContext.get(tradeRequest.getMethod()));
        if (contextOptional.isPresent()) {
            ServiceContext serviceContext = contextOptional.get();
            if (serviceContext.valid(tradeRequest.getParams().keySet())) {
                try {
                    Optional.ofNullable(serviceContext.invokeMethode(this, this.service, tradeRequest.getParams())).ifPresent(o -> {
                        if (MWork.get().isPrimitive(o.getClass())) tradeResponse.getResult().put("value", o);
                        else tradeResponse.setResult(new JSONObject(o));
                    });
                    tradeResponse.setTradeStatus(TradeStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    tradeResponse.setTradeStatus(TradeStatus.FAILED);
                }
            }else {
                tradeResponse.setTradeStatus(TradeStatus.BAD_REQUEST);
            }
        }else {
            tradeResponse.setTradeStatus(TradeStatus.METHOD_UNAVAILABLE);
        }
    }


    public Map<Class<?>, TypeWrap<?, String>> getTypeWrap() {
        return typeWrap;
    }
}
