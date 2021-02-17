package eu.mshadeproduction.mwork;

import eu.mshadeproduction.mwork.service.Service;
import eu.mshadeproduction.mwork.service.ServiceType;
import eu.mshadeproduction.mwork.service.TradeRequest;
import eu.mshadeproduction.mwork.service.TradeResponse;
import eu.mshadeproduction.mwork.streaming.Streaming;
import eu.mshadeproduction.mwork.streaming.StreamingListener;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;

public interface PacketDriver {

    void register(StreamingListener streamingListener);

    void emit(Session session, Streaming streaming, Receiver receiver);

    void query(Session session, Streaming streaming, Receiver receiver);

    void register(ServiceType serviceType, Service service);

    void emit(Session session, TradeRequest tradeRequest);

    CompletableFuture<TradeResponse> query(Session session, TradeRequest tradeRequest);

    boolean isValid(ContextType contextType, JSONObject jsonObject);

}
