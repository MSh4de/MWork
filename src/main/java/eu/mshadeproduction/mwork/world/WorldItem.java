package eu.mshadeproduction.mwork.world;

import java.util.Objects;

public class WorldItem {

    private String name;

    public WorldItem() { }

    public WorldItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorldItem)) return false;
        WorldItem worldItem = (WorldItem) o;
        return Objects.equals(name, worldItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
