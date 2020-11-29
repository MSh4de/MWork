package eu.mshadeproduction.mwork.packet;


import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public interface PacketCallBack<T> {

    void queue(PacketCallBackConsumer<T> packetResponse);

    void queue(PacketCallBackConsumer<T> packetResponse, Consumer<Exception> exception);

    T complete() throws ExecutionException, InterruptedException;

}
