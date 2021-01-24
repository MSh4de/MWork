package eu.mshadeproduction.mwork.service;

import org.json.JSONObject;

public class TradeRequest extends Trade {

    private ServiceType serviceType;
    private String method;
    private JSONObject params = new JSONObject();

    private TradeRequest() {
    }

    public TradeRequest(String method, ServiceType serviceType) {
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
}