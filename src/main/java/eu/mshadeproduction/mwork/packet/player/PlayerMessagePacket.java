package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketHandle;
import eu.mshadeproduction.mwork.packet.PacketSync;
import eu.mshadeproduction.mwork.packet.items.PlayerItem;

public class PlayerMessagePacket extends PacketHandle implements PacketSync {

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
