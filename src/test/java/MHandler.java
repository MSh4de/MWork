import eu.mshadeproduction.mwork.ContextType;
import eu.mshadeproduction.mwork.DefaultPacketDriver;
import eu.mshadeproduction.mwork.MWork;
import eu.mshadeproduction.mwork.Receiver;
import eu.mshadeproduction.mwork.service.TradeRequest;
import eu.mshadeproduction.mwork.service.TradeResponse;
import eu.mshadeproduction.mwork.streaming.StreamingType;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.json.JSONObject;

@WebSocket
public class MHandler {

    private final DefaultPacketDriver defaultPacketDriver = (DefaultPacketDriver) MWork.get().getPacketDriver();

    @OnWebSocketConnect
    public void onConnect(Session session){
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason){
    }

    @OnWebSocketError
    public void onError(Session session, Throwable throwable){
        //balek
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        JSONObject jsonObject = new JSONObject(message);
        if (jsonObject.has("contextType")) {
            ContextType.getContextTypeByName(jsonObject.getString("contextType")).ifPresent(contextType -> {
                switch (contextType){
                    case SERVICE:
                        defaultPacketDriver.complete(jsonObject.getInt("id")).ifPresent(completableFuture -> {
                            MWork.runAsync(() -> completableFuture.complete(MWork.get().deserialize(message, TradeResponse.class)));
                        }).ifNotPresent(() -> {
                            defaultPacketDriver.emit(session, MWork.get().deserialize(message, TradeRequest.class));
                        });
                        break;
                    case STREAMING:
                        StreamingType.getStreamingTypeByName(jsonObject.getString("streamingType")).ifPresent(streamingType -> {
                            defaultPacketDriver.emit(session, MWork.get().deserialize(message, streamingType.getStreamingClass()), MWork.get().deserialize(jsonObject.getString("receiver"), Receiver.class));
                        });
                        break;
                }
            });
        }
    }
}
