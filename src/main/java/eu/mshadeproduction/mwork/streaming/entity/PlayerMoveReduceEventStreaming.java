package eu.mshadeproduction.mwork.streaming.entity;

import eu.mshadeproduction.mwork.player.PlayerItem;

public class PlayerMoveReduceEventStreaming extends PlayerMoveEventStreaming {

    private PlayerMoveReduceEventStreaming(){
        super(null);
    }

    public PlayerMoveReduceEventStreaming(PlayerItem player) {
        super(player);
    }
}
