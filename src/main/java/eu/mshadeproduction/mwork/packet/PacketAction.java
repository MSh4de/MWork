package eu.mshadeproduction.mwork.packet;

import org.eclipse.jetty.websocket.api.Session;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface PacketAction<T extends Packet> {

   void subscribe(BiConsumer<Session, T> packetBus);

   void subscribe(BiConsumer<Session, T> packetBus, Consumer<Exception> exception);

   void subscribe(PacketBus<Session, T> packetBus);

   void subscribe(PacketBus<Session, T> packetBus, Consumer<Exception> exception);


}
