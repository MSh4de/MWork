package eu.mshadeproduction.mwork.packet;

import org.eclipse.jetty.websocket.api.Session;

@FunctionalInterface
public interface PacketBus<U extends Session, T extends Packet> {

    PacketResponse accept(U u, T t) throws Exception;

}
