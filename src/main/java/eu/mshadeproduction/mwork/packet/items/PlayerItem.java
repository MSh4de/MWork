package eu.mshadeproduction.mwork.packet.entity;

import eu.mshadeproduction.mwork.packet.world.LocationItem;

import java.util.UUID;

public class PlayerItem {

    private final UUID uuid;
    private final String name;
    private final LocationItem location;

    public PlayerItem(UUID uuid, String name, LocationItem location) {
        this.uuid = uuid;
        this.name = name;
        this.location = location;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public LocationItem getLocationItem() {
        return location;
    }

}
