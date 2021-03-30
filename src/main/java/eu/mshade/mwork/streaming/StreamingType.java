package eu.mshadeproduction.mwork.streaming;



import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum StreamingType {

    ;

    private final static Map<Class<? extends Streaming>, StreamingType> CLASS_STREAMING_TYPE_HASH_MAP = new HashMap<>();
    private final static Map<String, StreamingType> STRING_STREAMING_TYPE_MAP = new HashMap<>();
    static {
        for (StreamingType streamingType : StreamingType.values()) {
            CLASS_STREAMING_TYPE_HASH_MAP.put(streamingType.getStreamingClass(), streamingType);
            STRING_STREAMING_TYPE_MAP.put(streamingType.name(), streamingType);
        }
    }

    private final Class<? extends Streaming> aClass;

    StreamingType(Class<? extends Streaming> aClass) {
        this.aClass = aClass;
    }

    public Class<? extends Streaming> getStreamingClass() {
        return aClass;
    }

    public static Optional<StreamingType> getStreamingTypeByClass(Class<? extends Streaming> aClass){
        return Optional.ofNullable(CLASS_STREAMING_TYPE_HASH_MAP.get(aClass));
    }

    public static Optional<StreamingType> getStreamingTypeByName(String name){
        return Optional.ofNullable(STRING_STREAMING_TYPE_MAP.get(name));
    }
}
