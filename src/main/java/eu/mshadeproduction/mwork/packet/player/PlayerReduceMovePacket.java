package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.block.BlockItem;
import eu.mshadeproduction.mwork.packet.items.PlayerItem;
import eu.mshadeproduction.mwork.packet.items.ServerItem;

public class PlayerReduceMovePacket extends PlayerMovePacket{

    public PlayerReduceMovePacket(PlayerItem playerItem, BlockItem blockItem, ServerItem serverItem) {
        super(playerItem, blockItem, serverItem);
    }
}
