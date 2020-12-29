package eu.mshadeproduction.mwork.service;

import org.eclipse.jetty.websocket.api.Session;

@FunctionalInterface
public interface Service {

    TradeBucket handle(Session session, TradeBucket tradeBucket);

}
