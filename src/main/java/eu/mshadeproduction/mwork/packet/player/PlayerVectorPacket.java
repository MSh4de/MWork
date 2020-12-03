package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketHandle;
import eu.mshadeproduction.mwork.packet.PacketSync;
import eu.mshadeproduction.mwork.packet.items.PlayerItem;


public class PlayerVectorPacket extends PacketHandle implements PacketSync {

    private final PlayerItem player;
    private final double x, y, z;

    public PlayerVectorPacket(PlayerItem player, double x, double y, double z) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PlayerItem getPlayerItem() {
        return player;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
