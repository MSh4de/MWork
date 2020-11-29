package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketHandle;
import eu.mshadeproduction.mwork.packet.entity.PlayerItem;

public class PlayerMessagePacket extends PacketHandle {

    private final PlayerItem player;
    private final String message;

    public PlayerMessagePacket(PlayerItem player, String message) {
        this.player = player;
        this.message = message;
    }

    public PlayerItem getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }
}
