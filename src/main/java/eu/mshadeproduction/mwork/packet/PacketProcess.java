package eu.mshadeproduction.mwork.packet;

import org.eclipse.jetty.websocket.api.Session;

public interface PacketProcess {

    void reply(Session session, Packet packet, PacketResponse packetResponse);

    void query(Session session, Packet packet);

    <T extends PacketResponse> PacketCallBack<T> query(Session session, Packet packet, Class<T> aClass);

}
