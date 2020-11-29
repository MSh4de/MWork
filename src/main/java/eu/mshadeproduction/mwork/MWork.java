package eu.mshadeproduction.mwork;

import com.google.gson.Gson;
import eu.mshadeproduction.mwork.packet.PacketDispatcher;
import eu.mshadeproduction.mwork.packet.PacketProcess;

public final class MWork {

    private final Gson gson = new Gson();
    private final PacketProcess packetProcess = new DefaultPacketProcess(gson);
    private final PacketDispatcher packetDispatcher = new DefaultPacketDispatcher(packetProcess);
    private static MWork mWork;

    private MWork() {
        mWork = this;
    }

    public Gson getGson() {
        return gson;
    }

    public PacketProcess getPacketProcess() {
        return packetProcess;
    }

    public PacketDispatcher getPacketDispatcher() {
        return packetDispatcher;
    }

    public static MWork get(){
        return (mWork != null ? mWork : new MWork());
    }
}
