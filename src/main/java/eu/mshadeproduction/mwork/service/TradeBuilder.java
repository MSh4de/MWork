package eu.mshadeproduction.mwork.service;

public class TradeBuilder {

    private final TradeBucket tradeBucket;

    private TradeBuilder(ServiceType serviceType) {
        this.tradeBucket = new TradeBucket(serviceType);
    }
    private TradeBuilder(int id, ServiceType serviceType) {
        this.tradeBucket = new TradeBucket(id, serviceType);
    }
    private TradeBuilder(TradeBucket tradeBucket) {
        this.tradeBucket = tradeBucket;
    }

    public static TradeBuilder newTrade(ServiceType serviceType){
        return new TradeBuilder(serviceType);
    }

    public static TradeBuilder newTrade(int id, ServiceType serviceType){
        return new TradeBuilder(id, serviceType);
    }

    public static TradeBuilder from(TradeBucket tradeBucket){
        return new TradeBuilder(tradeBucket);
    }

}
