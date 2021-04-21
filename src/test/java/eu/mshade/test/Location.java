package eu.mshade.test;

import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.marshal.BinaryTagProperty;

import java.util.UUID;

@BinaryTagProperty(BinaryTagType.SHADE_COMPOUND)
public class Location {

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
