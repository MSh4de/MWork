package eu.mshadeproduction.mwork.packet.player.particle;


public class ColorItem {

    private final int red, green, blue;

    public ColorItem(int red, int green, int blue) {
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
