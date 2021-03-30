package eu.mshadeproduction.mwork;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ContextType {

    STREAMING,
    SERVICE;

    private static final Map<String, ContextType> MAP = new HashMap<>();

    static {
        for (ContextType contextType : ContextType.values()) {
            MAP.put(contextType.name(), contextType);
        }
    }

    public static Optional<ContextType> getContextTypeByName(String name){
        return Optional.ofNullable(MAP.get(name));
    }

}
