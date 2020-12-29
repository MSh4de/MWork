package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.streaming.TradeStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeItem {

    private String method;
    private final List<TradeContext> parameters = new ArrayList<>();
    private final List<TradeContext> result = new ArrayList<>();
    private TradeStatus tradeStatus = TradeStatus.REQUEST;

    public TradeItem() {
    }

    public TradeItem(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public List<TradeContext> getParameters() {
        return parameters;
    }

    public List<TradeContext> getResults() {
        return result;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public TradeItem setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
        return this;
    }
}
