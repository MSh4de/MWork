package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketResponse;
import eu.mshadeproduction.mwork.packet.entity.PlayerItem;

public class PlayerEyeVectorPacket extends PlayerVectorPacket implements PacketResponse {


    private Vector direction;

    public PlayerEyeVectorPacket(PlayerItem player, double x, double y, double z, Vector direction) {
        super(player, x, y, z);
        this.direction = direction;
    }

    public Vector getDirection() {
        return direction;
    }
}
