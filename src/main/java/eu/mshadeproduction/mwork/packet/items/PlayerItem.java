package eu.mshadeproduction.mwork.packet.items;

import eu.mshadeproduction.mwork.packet.world.LocationItem;

import java.util.UUID;

public class PlayerItem {

    private final UUID uuid;
    private final String name;

    public PlayerItem(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }


}
