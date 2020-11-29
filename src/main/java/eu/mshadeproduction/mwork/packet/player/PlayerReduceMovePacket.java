package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.block.BlockItem;
import eu.mshadeproduction.mwork.packet.entity.PlayerItem;
import eu.mshadeproduction.mwork.packet.entity.ServerItem;

public class PlayerReduceMovePacket extends PlayerMovePacket{

    public PlayerReduceMovePacket(PlayerItem playerItem, BlockItem blockItem, ServerItem serverItem) {
        super(playerItem, blockItem, serverItem);
    }
}
