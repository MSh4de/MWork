package eu.mshadeproduction.mwork;

import eu.mshadeproduction.mwork.service.ServiceType;
import eu.mshadeproduction.mwork.service.TradeRequest;
import eu.mshadeproduction.mwork.service.TradeResponse;
import eu.mshadeproduction.mwork.streaming.StreamingType;
import eu.mshadeproduction.mwork.streaming.entity.PlayerMoveEventStreaming;
import eu.mshadeproduction.mwork.world.LocationItem;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.json.JSONObject;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@WebSocket
public class Main {

    private final DefaultPacketDriver defaultPacketDriver = (DefaultPacketDriver) MWork.get().getPacketDriver();
    public Main() {
        defaultPacketDriver.register((session, streaming) -> {
            if (streaming instanceof PlayerMoveEventStreaming) {
                PlayerMoveEventStreaming playerMoveEventStreaming = (PlayerMoveEventStreaming) streaming;
                TradeRequest tradeRequest = new TradeRequest("getLocation", ServiceType.PLAYER);
                tradeRequest.getParams().put("player", playerMoveEventStreaming.getPlayer());
                defaultPacketDriver.query(session, tradeRequest).thenAccept(tradeResponse -> {
                       TradeRequest setBlock = new TradeRequest("setBlock", ServiceType.WORLD);
                       setBlock.getParams().put("location", tradeResponse.get(LocationItem.class));
                       setBlock.getParams().put("itemstack", new MItemStack(MMaterial.WHITE_STAINED_GLASS, 0, 0));
                       defaultPacketDriver.query(session, tradeRequest);
                });
            }
        });
    }

    public static void main(String[] args) throws Exception {
        final WebSocketClient client = new WebSocketClient();
        client.start();
        System.out.println(Integer.MAX_VALUE);
        final URI uri = new URI("ws://localhost:8080/mshade");
        final ClientUpgradeRequest clientUpgradeRequest = new ClientUpgradeRequest();
        client.connect(new Main(), uri, clientUpgradeRequest).get().getRemote();

    }

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
            System.out.println(message);
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
                            defaultPacketDriver.emit(session, MWork.get().deserialize(message, streamingType.getStreamingClass()));
                        });
                        break;
                }
            });
        }
    }
}
