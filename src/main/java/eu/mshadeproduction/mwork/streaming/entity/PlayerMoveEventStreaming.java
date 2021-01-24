package eu.mshadeproduction.mwork.streaming.entity;

import eu.mshadeproduction.mwork.player.PlayerItem;
import eu.mshadeproduction.mwork.streaming.Streaming;

public class PlayerMoveEventStreaming extends Streaming {

    private PlayerItem player;

    private PlayerMoveEventStreaming() {
    }

    public PlayerMoveEventStreaming(PlayerItem player) {
        this.player = player;
    }


    public PlayerItem getPlayer() {
        return player;
    }
}
