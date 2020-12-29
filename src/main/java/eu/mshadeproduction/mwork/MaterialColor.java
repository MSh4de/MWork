package eu.mshadeproduction.mwork;

public enum  MaterialColor {

    WHITE(0),
    ORANGE(1),
    MAGENTA(2),
    LIGHT_BLUE(3),
    YELLOW(4),
    LIME(5),
    PINK(6),
    GRAY(7),
    SILVER(8),
    CYAN(9),
    PURPLE(10),
    BLUE(11),
    BROWN(12),
    GREEN(13),
    RED(14),
    BLACK(15);

    private final int data;
    private MaterialColor(int data) {
        this.data = data;
    }

    public short getData() {
        return (short) data;
    }

    public static MaterialColor getByData(int data){
        for (MaterialColor value : MaterialColor.values()) {
            if(value.getData() == data) return value;
        }
        throw new NullPointerException();
    }

}
