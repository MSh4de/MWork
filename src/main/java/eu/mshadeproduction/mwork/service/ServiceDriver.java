package eu.mshadeproduction.mwork.service;

import org.eclipse.jetty.websocket.api.Session;

import java.util.concurrent.CompletableFuture;

public interface ServiceDriver {

    void register(ServiceType serviceType, Service service);

    void emit(Session session, TradeBucket tradeBucket);

    CompletableFuture<TradeBucket> query(Session session, TradeBucket tradeBucket);

}
