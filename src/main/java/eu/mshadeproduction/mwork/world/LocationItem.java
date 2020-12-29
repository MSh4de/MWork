package eu.mshadeproduction.mwork.world;

public class LocationItem {

    private WorldItem world;
    private double x, y, z;
    private float pitch, yaw;

    public LocationItem() {
    }

    public LocationItem(WorldItem world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }
    public LocationItem(WorldItem world, int x, int y, int z) {
        this(world, x, y, z, 0, 0);
    }

    public LocationItem(WorldItem world, double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
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

    public WorldItem getWorld() {
        return world;
    }


}
