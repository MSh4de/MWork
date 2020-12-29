package eu.mshadeproduction.mwork.streaming;

import eu.mshadeproduction.mwork.MWork;
import org.eclipse.jetty.websocket.api.Session;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DefaultStreamingDriver implements StreamingDriver {

    private final Queue<StreamingListener> streamingListeners = new ConcurrentLinkedQueue<>();

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
        MWork.get().serialize(streaming).thenAccept(s -> session.getRemote().sendStringByFuture(s));
    }
}
