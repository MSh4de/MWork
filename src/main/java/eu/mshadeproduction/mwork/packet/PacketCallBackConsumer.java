package eu.mshadeproduction.mwork.packet;

@FunctionalInterface
public interface PacketCallBackConsumer<T> {

    void accept(T t) throws Exception;

}
