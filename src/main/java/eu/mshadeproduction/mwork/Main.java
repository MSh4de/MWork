package eu.mshadeproduction.mwork;

import eu.mshadeproduction.mwork.dispatcher.DefaultDispatcherDriver;
import eu.mshadeproduction.mwork.streaming.Streaming;
import eu.mshadeproduction.mwork.streaming.entity.PlayerJoinEventStreaming;
import eu.mshadeproduction.mwork.streaming.entity.PlayerMoveEventStreaming;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class Main {
    public Main() {

        DefaultDispatcherDriver<Streaming> driver = new DefaultDispatcherDriver<>();
        driver.register(PlayerJoinEventStreaming.class, (event, dispatcherContainer) -> {

        });
    }

    /**
    private final DefaultPacketDriver defaultPacketDriver = (DefaultPacketDriver) MWork.get().getPacketDriver();
    private final LinkedHashMap<LocationItem, MItemStack> locationItemMItemStack = new LinkedHashMap<>();
    private final List<MMaterial> mMaterials = new ArrayList<>();
    private final Random random = new Random();


    public Main() {
        for (MColorType mColorType : MColorType.values()) {
            MMaterial.getMaterialByName(mColorType.name()+"_STAINED_GLASS").ifPresent(mMaterials::add);
        }
    }

    public void onEnable(Session s){
        MWork.get().getScheduledExecutorService().scheduleAtFixedRate(() -> {
            locationItemMItemStack.entrySet().stream().findFirst().ifPresent(entry -> {
                setBlock(s, entry.getKey(), entry.getValue());
                locationItemMItemStack.remove(entry.getKey());
            });
        }, 0, 300, TimeUnit.MILLISECONDS);

        defaultPacketDriver.register((session, streaming) -> {
            if (streaming instanceof PlayerMoveEventStreaming) {
                PlayerMoveEventStreaming playerMoveEventStreaming = (PlayerMoveEventStreaming) streaming;
                getLocation(session, playerMoveEventStreaming.getPlayer()).thenAccept(locationItem -> {
                    LocationItem location = locationItem.addY(-1).toBlock();
                    if (!locationItemMItemStack.containsKey(location)) {
                        getBlock(session, location).thenAccept(mItemStack -> {
                            if (mItemStack.getMaterial() != MMaterial.AIR) {
                                locationItemMItemStack.put(location, mItemStack);
                                setBlock(session, locationItem, new MItemStack(mMaterials.get(random.nextInt(mMaterials.size())), 0, 1));
                            }
                        });
                    }
                });
            }
        });
    }

    public CompletableFuture<LocationItem> getLocation(Session session, PlayerItem playerItem){
        CompletableFuture<LocationItem> completableFuture = new CompletableFuture<>();
        TradeRequest getLocation = new TradeRequest("getLocation", ServiceType.PLAYER);
        getLocation.getParams().put("player", playerItem);
        defaultPacketDriver.query(session, getLocation).thenAccept(tradeResponse -> completableFuture.complete(tradeResponse.get(LocationItem.class)));
        return completableFuture;
    }

    public CompletableFuture<MItemStack> getBlock(Session session, LocationItem locationItem){
        CompletableFuture<MItemStack> completableFuture = new CompletableFuture<>();
        TradeRequest block = new TradeRequest("getBlock", ServiceType.WORLD);
        block.getParams().put("location", locationItem);
        defaultPacketDriver.query(session, block).thenAccept(tradeResponse -> {
            completableFuture.complete(tradeResponse.get(MItemStack.class));
        });
        return completableFuture;
    }

    public void setBlock(Session session, LocationItem locationItem, MItemStack mItemStack){
        TradeRequest block = new TradeRequest("setBlock", ServiceType.WORLD);
        block.getParams().put("location", locationItem);
        block.getParams().put("itemstack", mItemStack);
        defaultPacketDriver.query(session, block);
    }


    public static void main(String[] args) throws Exception {
        final WebSocketClient client = new WebSocketClient();
        client.start();
        System.out.println(Integer.MAX_VALUE);
        final URI uri = new URI("ws://localhost:8080/mshade");
        final ClientUpgradeRequest clientUpgradeRequest = new ClientUpgradeRequest();
        Main main = new Main();
        main.onEnable(client.connect(main, uri, clientUpgradeRequest).get());

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
    **/
}
