package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketResponse;
import eu.mshadeproduction.mwork.packet.entity.PlayerItem;

public class PlayerEyeLocationPacket extends PlayerVectorPacket implements PacketResponse {


    public PlayerEyeLocationPacket(PlayerItem player, double x, double y, double z) {
        super(player, x, y, z);
    }

}
