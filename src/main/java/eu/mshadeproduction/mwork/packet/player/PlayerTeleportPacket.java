package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.PacketHandle;
import eu.mshadeproduction.mwork.packet.PacketSync;
import eu.mshadeproduction.mwork.packet.items.PlayerItem;
import eu.mshadeproduction.mwork.packet.world.LocationItem;

public class PlayerTeleportPacket extends PacketHandle implements PacketSync {

    private final PlayerItem player;
    private final LocationItem location;

    public PlayerTeleportPacket(PlayerItem playerItem, LocationItem locationItem) {
        this.player = playerItem;
        this.location = locationItem;
    }

    public PlayerItem getPlayerItem() {
        return player;
    }

    public LocationItem getLocationItem() {
        return location;
    }


    @Override
    public String toString() {
        return String.format("(player=%s, location=%s", player.toString(), location.toString());
    }
}
