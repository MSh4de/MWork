package eu.mshadeproduction.mwork.world;

import java.util.Objects;

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

    public LocationItem setWorld(WorldItem world) {
        this.world = world;
        return this;
    }

    public LocationItem setX(double x) {
        this.x = x;
        return this;
    }

    public LocationItem setY(double y) {
        this.y = y;
        return this;
    }

    public LocationItem setZ(double z) {
        this.z = z;
        return this;
    }

    public LocationItem addX(double x) {
        this.x += x;
        return this;
    }

    public LocationItem addY(double y) {
        this.y += y;
        return this;
    }

    public LocationItem addZ(double z) {
        this.z += z;
        return this;
    }

    public LocationItem setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public LocationItem setYaw(float yaw) {
        this.yaw = yaw;
        return this;
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

    public LocationItem toBlock(){
        return new LocationItem(world, locToBlock(x), locToBlock(y), locToBlock(z));
    }

    private static int locToBlock(double loc) {
        final int floor = (int) loc;
        return floor == loc ? floor : floor - (int) (Double.doubleToRawLongBits(loc) >>> 63);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationItem)) return false;
        LocationItem that = (LocationItem) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.z, z) == 0 && Float.compare(that.pitch, pitch) == 0 && Float.compare(that.yaw, yaw) == 0 && Objects.equals(world, that.world);
    }


    @Override
    public int hashCode() {
        return Objects.hash(world, x, y, z, pitch, yaw);
    }
}
