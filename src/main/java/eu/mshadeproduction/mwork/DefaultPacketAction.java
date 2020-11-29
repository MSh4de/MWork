package eu.mshadeproduction.mwork;

import eu.mshadeproduction.mwork.packet.Packet;
import eu.mshadeproduction.mwork.packet.PacketAction;
import eu.mshadeproduction.mwork.packet.PacketBus;
import eu.mshadeproduction.mwork.packet.PacketResponse;
import org.eclipse.jetty.websocket.api.Session;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class DefaultPacketAction<T extends Packet> implements PacketAction<T> {

    private final Class<T> packetClass;
    private Optional<BiConsumer<Session, T>> biConsumer = Optional.empty();
    private Optional<PacketBus<Session, T>> packetBus = Optional.empty();
    private Optional<Consumer<Exception>> exception = Optional.empty();

    public DefaultPacketAction(Class<T> packetClass) {
        this.packetClass = packetClass;
    }

    @Override
    public void subscribe(BiConsumer<Session, T> packetBus) {
        this.subscribe(packetBus, null);
    }

    @Override
    public void subscribe(BiConsumer<Session, T> packetBus, Consumer<Exception> exception) {
        this.biConsumer = Optional.of(packetBus);
        this.exception = Optional.ofNullable(exception);
    }

    @Override
    public void subscribe(PacketBus<Session, T> packetBus) {
        this.subscribe(packetBus, null);
    }

    @Override
    public void subscribe(PacketBus<Session, T> packetBus, Consumer<Exception> exception) {
        this.packetBus = Optional.of(packetBus);
        this.exception = Optional.ofNullable(exception);
    }

    public Optional<PacketResponse> join(Session session, Packet t) throws Exception{
        if (packetBus.isPresent()) return Optional.ofNullable(packetBus.get().accept(session, (T) t));
        biConsumer.ifPresent(consumer -> consumer.accept(session, (T) t));
        return Optional.empty();
    }

    public Optional<Consumer<Exception>> getException() {
        return exception;
    }

    public Class<T> getPacketClass() {
        return packetClass;
    }
}
