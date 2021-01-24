package eu.mshadeproduction.mwork.player;

import java.util.UUID;

public class PlayerItem {

    private UUID uuid;
    private String name;

    private PlayerItem() { }

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
