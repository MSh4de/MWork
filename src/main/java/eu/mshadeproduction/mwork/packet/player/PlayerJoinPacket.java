package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.Packet;
import eu.mshadeproduction.mwork.packet.items.PlayerItem;
import eu.mshadeproduction.mwork.packet.items.ServerItem;

public class PlayerJoinPacket extends Packet {

    private final PlayerItem player;
    private final ServerItem server;

    public PlayerJoinPacket(PlayerItem playerItem, ServerItem serverItem) {
        this.player = playerItem;
        this.server = serverItem;
    }

    public PlayerItem getPlayerItem() {
        return player;
    }

    public ServerItem getServerItem() {
        return server;
    }
}
