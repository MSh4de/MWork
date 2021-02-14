package eu.mshadeproduction.mwork;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum MColorType {

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
    MColorType(int data) {
        this.data = data;
    }

    private final static Map<String, MColorType> STRING_MATERIAL_COLOR_MAP = new HashMap<>();
    private final static Map<Integer, MColorType> INTEGER_MATERIAL_COLOR_MAP = new HashMap<>();
    static {
        for (MColorType mColorType : MColorType.values()) {
            STRING_MATERIAL_COLOR_MAP.put(mColorType.name(), mColorType);
            INTEGER_MATERIAL_COLOR_MAP.put(mColorType.data, mColorType);
        }
    }

    public short getData() {
        return (short) data;
    }

    public static Optional<MColorType> getByData(int data){
        return Optional.ofNullable(INTEGER_MATERIAL_COLOR_MAP.get(data));
    }
    public static Optional<MColorType> getByName(String name){
        return Optional.ofNullable(STRING_MATERIAL_COLOR_MAP.get(name));
    }

}
