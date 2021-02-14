package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.MWork;
import eu.mshadeproduction.mwork.Receiver;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;

public class TradeResponse extends Trade{

    private JSONObject result = new JSONObject();
    private TradeStatus tradeStatus = TradeStatus.OK;

    private TradeResponse() {
        super(0, null);
    }

    public TradeResponse(int id, Receiver receiver) {
        super(id, receiver);
    }


    public <T> T get(String key, Class<T> aClass) {
        if (MWork.get().isPrimitive(aClass)) {
            return aClass.cast(result.get(key));
        }
        return MWork.get().deserialize(result.getString(key), aClass);
    }
    public <T> T get(Class<T> aClass) {
        return MWork.get().deserialize(result.toString(), aClass);
    }

    public void setResult(JSONObject result){
        this.result = result;
    }

    public JSONObject getResult() {
        return result;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public TradeResponse setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
        return this;
    }

    @Override
    public String toString() {
        return "TradeResponse{" +
                "result=" + result +
                ", tradeStatus=" + tradeStatus +
                '}';
    }
}
