package eu.mshadeproduction.mwork.streaming;

import eu.mshadeproduction.mwork.Receiver;
import org.eclipse.jetty.websocket.api.Session;

@FunctionalInterface
public interface StreamingListener {

    void handle(Session session, Streaming streaming, Receiver receiver);

}
