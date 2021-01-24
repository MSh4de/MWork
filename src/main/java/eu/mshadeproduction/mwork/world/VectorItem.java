package eu.mshadeproduction.mwork.world;

public class VectorItem {

    protected double x, y, z;

    private VectorItem(){ }

    public VectorItem(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public VectorItem(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public VectorItem(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
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

    public VectorItem setX(double x) {
        this.x = x;
        return this;
    }

    public VectorItem setY(double y) {
        this.y = y;
        return this;
    }

    public VectorItem setZ(double z) {
        this.z = z;
        return this;
    }

    public VectorItem setX(int x) {
        this.x = x;
        return this;
    }

    public VectorItem setY(int y) {
        this.y = y;
        return this;
    }

    public VectorItem setZ(int z) {
        this.z = z;
        return this;
    }

    public VectorItem setX(float x) {
        this.x = x;
        return this;
    }

    public VectorItem setY(float y) {
        this.y = y;
        return this;
    }

    public VectorItem setZ(float z) {
        this.z = z;
        return this;
    }
}
