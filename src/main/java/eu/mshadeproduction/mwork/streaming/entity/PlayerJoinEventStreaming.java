package eu.mshadeproduction.mwork.streaming.entity;

import eu.mshadeproduction.mwork.player.PlayerItem;
import eu.mshadeproduction.mwork.streaming.Streaming;

public class PlayerJoinEventStreaming extends Streaming {

    private final PlayerItem player;

    public PlayerJoinEventStreaming(PlayerItem player) {
        this.player = player;
    }

    public PlayerItem getPlayer() {
        return player;
    }
}
