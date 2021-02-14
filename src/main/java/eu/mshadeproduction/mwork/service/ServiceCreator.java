package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.MWork;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.*;

public class ServiceCreator {

    private final Service service;
    private final Map<String, ServiceContext> serviceContext = new HashMap<>();

    public ServiceCreator(Service service) {
        this.service = service;

        for (Method method : service.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(TradeAction.class)) {
                ServiceContext serviceContext = new ServiceContext(method);
                this.serviceContext.put(method.getAnnotation(TradeAction.class).value(), serviceContext);
            }
        }
    }

    public void invokeService(Session session, TradeRequest tradeRequest, TradeResponse tradeResponse){
        Optional<ServiceContext> contextOptional = Optional.ofNullable(this.serviceContext.get(tradeRequest.getMethod()));
        if (contextOptional.isPresent()) {
            ServiceContext serviceContext = contextOptional.get();
            //serviceContext.valid(tradeRequest.getParams().keySet()
            if (true) {
                try {
                    Optional.ofNullable(serviceContext.invokeMethode(session, this.service, tradeRequest.getParams())).ifPresent(o -> {
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
}
