package eu.mshadeproduction.mwork.packet;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancel);

}
