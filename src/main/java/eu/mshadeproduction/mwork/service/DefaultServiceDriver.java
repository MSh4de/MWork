package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.MWork;
import org.eclipse.jetty.websocket.api.Session;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultServiceDriver implements ServiceDriver {

    private final ConcurrentMap<ServiceType, Service> services = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, CompletableFuture<TradeBucket>> futureConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public void register(ServiceType serviceType, Service service) {
        this.services.put(serviceType, service);
    }

    @Override
    public void emit(Session session, TradeBucket tradeBucket) {
        MWork.get().serialize(this.services.get(tradeBucket.getServiceType()).handle(session, tradeBucket)).thenAccept(s -> session.getRemote().sendStringByFuture(s));
    }

    @Override
    public CompletableFuture<TradeBucket> query(Session session, TradeBucket tradeBucket) {
        CompletableFuture<TradeBucket> completableFuture = new CompletableFuture<>();
        this.futureConcurrentMap.put(tradeBucket.getId(), completableFuture);
        MWork.get().serialize(tradeBucket).thenAccept(s -> session.getRemote().sendStringByFuture(s));
        return completableFuture;
    }

    public Optional<CompletableFuture<TradeBucket>> completableFuture(int id){
        return Optional.ofNullable(futureConcurrentMap.remove(id));
    }
}
