package eu.mshadeproduction.mwork.service;

public class TradeContext {

    private TradeContextType objectContext;
    private Object object;

    public TradeContext(TradeContextType objectContext, Object object) {
        this.objectContext = objectContext;
        this.object = object;
    }

    public TradeContext() { }

    public TradeContextType getObjectContext() {
        return objectContext;
    }

    public Object getObject() {
        return object;
    }

    public TradeContext setObject(Object object) {
        this.object = object;
        return this;
    }

    public <T> T get(Class<T> aClass){
        return aClass.cast(object);
    }
}
