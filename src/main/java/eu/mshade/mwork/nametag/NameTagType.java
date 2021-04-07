package eu.mshade.mwork.nametag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NameTagType {

    BYTE(byte.class, Byte.class),
    SHORT(short.class, Short.class),
    INTEGER(int.class, Integer.class),
    LONG(long.class, Long.class),
    FLOAT(float.class, Float.class),
    DOUBLE(double.class, Double.class),
    STRING(String.class),
    OBJECT(),
    ARRAY(),
    LIST(List.class);

    private final static Map<Class<?>, NameTagType> MAP = new HashMap<>();
    static {
        for (NameTagType nameTagType : NameTagType.values()) {
            for (Class<?> aClass : nameTagType.classes) {
                MAP.put(aClass, nameTagType);
            }
        }
    }

    private final List<Class<?>> classes;

    NameTagType(Class<?>... classes) {
        this.classes = Arrays.asList(classes);
    }

    public static NameTagType getNameTagType(Class<?> aClass){
        return MAP.getOrDefault(aClass, OBJECT);
    }
}
