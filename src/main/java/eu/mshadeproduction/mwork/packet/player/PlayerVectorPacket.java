package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketHandle;
import eu.mshadeproduction.mwork.packet.entity.PlayerItem;


public class PlayerVectorPacket extends PacketHandle {

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
