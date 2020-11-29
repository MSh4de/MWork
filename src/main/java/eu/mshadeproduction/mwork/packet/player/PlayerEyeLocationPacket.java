package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketResponse;
import eu.mshadeproduction.mwork.packet.entity.PlayerItem;

public class PlayerEyeLocationPacket extends PlayerVectorPacket implements PacketResponse {

    private final float yaw, pitch;

    public PlayerEyeLocationPacket(PlayerItem player, double x, double y, double z, float yaw, float pitch) {
        super(player, x, y, z);
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
