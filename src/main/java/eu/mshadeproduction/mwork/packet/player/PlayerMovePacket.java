package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.Packet;
import eu.mshadeproduction.mwork.packet.block.BlockItem;
import eu.mshadeproduction.mwork.packet.entity.PlayerItem;
import eu.mshadeproduction.mwork.packet.entity.ServerItem;

public class PlayerMovePacket extends Packet {

    private final PlayerItem player;
    private final ServerItem server;
    private final BlockItem block;

    public PlayerMovePacket(PlayerItem playerItem, BlockItem blockItem, ServerItem serverItem) {
        this.player = playerItem;
        this.block = blockItem;
        this.server = serverItem;
    }

    public BlockItem getBlockItem() {
        return block;
    }

    public PlayerItem getPlayerItem() {
        return player;
    }

    public ServerItem getServerItem() {
        return server;
    }

}
