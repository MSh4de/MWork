package eu.mshadeproduction.mwork.packet;

import org.eclipse.jetty.websocket.api.Session;

public interface PacketDispatcher {

    <T extends Packet> PacketAction<T> on(Class<T> aClass);

    <T extends Packet> void push(Session session, T packet);


}
