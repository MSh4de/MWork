package eu.mshadeproduction.mwork.packet.world;

public class LocationItem {

    private final WorldItem world;
    private final double x, y, z;
    private final float pitch, yaw;

    public LocationItem(WorldItem world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public LocationItem(WorldItem world, double x, double y, double z, float pitch, float yaw) {
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

    public WorldItem getWorldItem() {
        return world;
    }


}
