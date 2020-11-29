package eu.mshadeproduction.mwork;

import eu.mshadeproduction.mwork.packet.PacketCallBack;
import eu.mshadeproduction.mwork.packet.PacketCallBackConsumer;
import eu.mshadeproduction.mwork.packet.PacketResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class DefaultPacketCallBack<T extends PacketResponse> implements PacketCallBack<T> {

    private final static Logger logger = LoggerFactory.getLogger(DefaultPacketCallBack.class);

    private final Class<T> responseClass;
    private Optional<PacketCallBackConsumer<T>> callBackConsumer = Optional.empty();
    private Optional<Consumer<Exception>> exceptionConsumer = Optional.empty();
    private final CompletableFuture<T> completableFuture = new CompletableFuture<>();

    public DefaultPacketCallBack(Class<T> responseClass) {
        this.responseClass = responseClass;
    }

    @Override
    public void queue(PacketCallBackConsumer<T> packetResponse) {
        this.callBackConsumer = Optional.of(packetResponse);
    }

    @Override
    public void queue(PacketCallBackConsumer<T> packetResponse, Consumer<Exception> exception) {
        this.queue(packetResponse);
        this.exceptionConsumer = Optional.of(exception);
    }

    @Override
    public T complete() throws ExecutionException, InterruptedException {
        return completableFuture.get();
    }

    public void complete(T packetResponse){
        completableFuture.complete(packetResponse);
        this.callBackConsumer.ifPresent(callBackConsumer -> {
            try {
                callBackConsumer.accept(packetResponse);
            } catch (Exception e) {
                this.exceptionConsumer.ifPresent(exceptionConsumer -> exceptionConsumer.accept(e));
                if (!exceptionConsumer.isPresent()) logger.error("", e);
            }
        });
    }

    public Class<T> getResponseClass() {
        return responseClass;
    }
}
