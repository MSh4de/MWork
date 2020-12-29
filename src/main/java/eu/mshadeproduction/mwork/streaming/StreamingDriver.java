package eu.mshadeproduction.mwork.streaming;

import org.eclipse.jetty.websocket.api.Session;

public interface StreamingDriver {

    void register(StreamingListener streamingListener);

    void emit(Session session, Streaming streaming);

    void query(Session session, Streaming streaming);

}
