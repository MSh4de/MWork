package eu.mshadeproduction.mwork;

import com.google.gson.Gson;
import eu.mshadeproduction.mwork.packet.Packet;
import eu.mshadeproduction.mwork.packet.PacketCallBack;
import eu.mshadeproduction.mwork.packet.PacketProcess;
import eu.mshadeproduction.mwork.packet.PacketResponse;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Optional;

public class DefaultPacketProcess implements PacketProcess {

    private final HashMap<Integer, DefaultPacketCallBack<? extends PacketResponse>> defaultPacketCallBackHashMap = new HashMap<>();
    private final Gson gson;

    public DefaultPacketProcess(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void reply(Session session, Packet packet, PacketResponse packetResponse) {
        JSONObject jsonObject = new JSONObject(gson.toJson(packetResponse));
        jsonObject.put("packetType", packet.getPacketType());
        jsonObject.put("ID", packet.getID());
        sendString(session, jsonObject.toString());
    }

    @Override
    public void query(Session session, Packet packet) {
        JSONObject jsonObject = new JSONObject(gson.toJson(packet));
        jsonObject.put("ID", -1);
        sendString(session, jsonObject.toString());
    }

    @Override
    public <T extends PacketResponse> PacketCallBack<T> query(Session session, Packet packet, Class<T> aClass) {
        DefaultPacketCallBack<?> packetResponseDefaultRestAction = new DefaultPacketCallBack<>(aClass);
        defaultPacketCallBackHashMap.put(packet.getID(), packetResponseDefaultRestAction);
        sendString(session, gson.toJson(packet));
        return (PacketCallBack<T>) packetResponseDefaultRestAction;
    }

    public Optional<DefaultPacketCallBack<? extends PacketResponse>> getPacketCallBack(int id){
        return Optional.ofNullable(defaultPacketCallBackHashMap.remove(id));
    }

    public HashMap<Integer, DefaultPacketCallBack<? extends PacketResponse>> getDefaultPacketCallBackHashMap() {
        return defaultPacketCallBackHashMap;
    }

    public void sendString(Session session, String s){
        session.getRemote().sendStringByFuture(s);
    }

}
