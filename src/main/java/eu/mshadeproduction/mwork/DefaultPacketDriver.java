package eu.mshadeproduction.mwork;

import eu.mshadeproduction.mwork.service.*;
import eu.mshadeproduction.mwork.streaming.Streaming;
import eu.mshadeproduction.mwork.streaming.StreamingListener;
import eu.mshadeproduction.mwork.streaming.StreamingType;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class DefaultPacketDriver implements PacketDriver {

    private final Queue<StreamingListener> streamingListeners = new ConcurrentLinkedQueue<>();
    private final ConcurrentMap<ServiceType, ServiceCreator> services = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, CompletableFuture<TradeResponse>> futureConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public void register(StreamingListener streamingListener) {
        this.streamingListeners.add(streamingListener);
    }

    @Override
    public void emit(Session session, Streaming streaming) {
        streamingListeners.forEach(streamingListener -> streamingListener.handle(session, streaming));
    }

    @Override
    public void query(Session session, Streaming streaming) {
        JSONObject jsonObject = new JSONObject(MWork.get().serialize(streaming));
        jsonObject.put("contextType", ContextType.STREAMING);
        session.getRemote().sendStringByFuture(jsonObject.toString());
    }

    @Override
    public void register(ServiceType serviceType, Service service) {
        this.services.put(serviceType, new ServiceCreator(service));
    }

    @Override
    public void emit(Session session, TradeRequest tradeRequest) {
        Optional.ofNullable(this.services.get(tradeRequest.getServiceType())).ifPresent(service -> {
            TradeResponse tradeResponse = new TradeResponse(tradeRequest.getId());
            service.invokeService(tradeRequest, tradeResponse);
            session.getRemote().sendStringByFuture(MWork.get().serialize(tradeResponse));
        });
    }


    @Override
    public CompletableFuture<TradeResponse> query(Session session, TradeRequest tradeRequest) {
        CompletableFuture<TradeResponse> completableFuture = new CompletableFuture<>();
        this.futureConcurrentMap.put(tradeRequest.getId(), completableFuture);
        session.getRemote().sendStringByFuture(MWork.get().serialize(tradeRequest));
        return completableFuture;
    }


    public MOptional<CompletableFuture<TradeResponse>> complete(int id){
        return MOptional.ofNullable(futureConcurrentMap.remove(id));
    }

}
