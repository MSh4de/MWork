package eu.mshadeproduction.mwork;

import eu.mshadeproduction.mwork.packet.Packet;
import eu.mshadeproduction.mwork.packet.PacketAction;
import eu.mshadeproduction.mwork.packet.PacketDispatcher;
import eu.mshadeproduction.mwork.packet.PacketProcess;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DefaultPacketDispatcher implements PacketDispatcher {

    private final Queue<DefaultPacketAction<?>> packetListeners = new ConcurrentLinkedDeque<>();
    private final Logger logger = LoggerFactory.getLogger(DefaultPacketDispatcher.class);
    private final PacketProcess packetProcess;

    public DefaultPacketDispatcher(PacketProcess packetProcess) {
        this.packetProcess = packetProcess;
    }

    @Override
    public <T extends Packet> PacketAction<T> on(Class<T> aClass) {
        DefaultPacketAction<?> packetAction = new DefaultPacketAction<>(aClass);
        this.packetListeners.add(packetAction);
        return (PacketAction<T>) packetAction;
    }

    @Override
    public  <T extends Packet> void push(Session session, T packet) {
        this.packetListeners.stream().filter(defaultPacketAction -> defaultPacketAction.getPacketClass().isAssignableFrom(packet.getClass())).forEach(defaultPacketAction -> {
            try {
                defaultPacketAction.join(session, packet).ifPresent(packetResponse -> packetProcess.reply(session, packet, packetResponse));
            } catch (Exception e) {
                defaultPacketAction.getException().ifPresent(exceptionConsumer -> exceptionConsumer.accept(e));
                if (!defaultPacketAction.getException().isPresent()) logger.error("", e);
            }
        });
    }






}
