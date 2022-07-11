package eu.mshade.test;

import java.io.Serializable;
import java.util.UUID;

public class Location implements Serializable {

    private int x, y, z;
    private UUID uuid = UUID.randomUUID();
    private String name;

    public Location(int x, int y, int z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
