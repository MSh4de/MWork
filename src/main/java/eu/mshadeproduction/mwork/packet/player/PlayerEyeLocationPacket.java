package eu.mshadeproduction.mwork.packet.player;

import eu.mshadeproduction.mwork.packet.Packet;
import eu.mshadeproduction.mwork.packet.PacketResponse;
import eu.mshadeproduction.mwork.packet.items.PlayerItem;
import eu.mshadeproduction.mwork.packet.world.LocationItem;

public class PlayerEyeLocationPacket extends Packet implements PacketResponse {

    private final PlayerItem player;
    private final LocationItem location;

    public PlayerEyeLocationPacket(PlayerItem player, LocationItem location) {
        this.player = player;
        this.location = location;
    }

    public PlayerItem getPlayer() {
        return player;
    }

    public LocationItem getLocation() {
        return location;
    }
}
