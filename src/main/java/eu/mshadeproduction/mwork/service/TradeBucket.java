package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.ContextType;

import java.util.ArrayList;
import java.util.List;

public class TradeBucket {

    private static int index = 0;

    private int id;
    private ContextType contextType = ContextType.SERVICE;
    private final List<TradeContext> tradesContext = new ArrayList<>();
    private ServiceType serviceType;
    private List<TradeItem> tradeItems = new ArrayList<>();

    public TradeBucket() {
    }

    public TradeBucket(ServiceType serviceType) {
        this(index++, serviceType);
    }

    public TradeBucket(int id, ServiceType serviceType) {
        this.id = id;
        this.serviceType = serviceType;
    }


    public int getId() {
        return id;
    }

    public ContextType getContextType() {
        return contextType;
    }

    public List<TradeContext> getTradesContext() {
        return tradesContext;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void addTrade(TradeItem tradeItem){
        this.tradeItems.add(tradeItem);
    }

    public List<TradeItem> getTrades(){
        return this.tradeItems;
    }
}
