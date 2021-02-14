package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.Receiver;
import org.json.JSONObject;

public class TradeRequest extends Trade {

    private ServiceType serviceType;
    private String method;
    private JSONObject params = new JSONObject();

    private TradeRequest() {
        super(null);
    }

    public TradeRequest(String method, ServiceType serviceType, Receiver receiver) {
        super(receiver);
        this.serviceType = serviceType;
        this.method = method;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public JSONObject getParams() {
        return params;
    }

    public TradeRequest setParams(JSONObject params) {
        this.params = params;
        return this;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "TradeRequest{" +
                "contextType= "+ getContextType()+
                ", receiver= "+ getReceiver()+
                ", serviceType=" + serviceType +
                ", method='" + method + '\'' +
                ", params=" + params +
                '}';
    }
}