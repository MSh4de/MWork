package eu.mshadeproduction.mwork.world;

public class MColor {

    private int red, green, blue;

    private MColor() { }

    public MColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
