package eu.mshadeproduction.mwork.packet.world;

import eu.mshadeproduction.mwork.packet.player.Vector;

public class LocationItem {

    private final WorldItem world;
    private final double x, y, z;
    private final float pitch, yaw;
    private final Vector direction;

    public LocationItem(WorldItem world, Vector direction, double x, double y, double z) {
        this(world, direction, x, y, z, 0, 0);
    }
    public LocationItem(WorldItem world, double x, double y, double z) {
        this(world, new Vector(0,0,0), x, y, z, 0, 0);
    }

    public LocationItem(WorldItem world, Vector direction, double x, double y, double z, float pitch, float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
        this.direction = direction;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public Vector getDirection() {
        return direction;
    }

    public WorldItem getWorldItem() {
        return world;
    }


}
