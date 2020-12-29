package eu.mshadeproduction.mwork.streaming;



import eu.mshadeproduction.mwork.streaming.entity.PlayerJoinEventStreaming;
import eu.mshadeproduction.mwork.streaming.entity.PlayerMoveEventStreaming;
import eu.mshadeproduction.mwork.streaming.entity.PlayerMoveReduceEventStreaming;
import eu.mshadeproduction.mwork.streaming.entity.PlayerQuiEventStreaming;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum StreamingType {

    PLAYER_JOIN_EVENT_STREAMING(PlayerJoinEventStreaming.class),
    PLAYER_MOVE_EVENT_STREAMING(PlayerMoveEventStreaming.class),
    PLAYER_MOVE_REDUCE_EVENT_STREAMING(PlayerMoveReduceEventStreaming.class),
    PLAYER_QUIT_EVENT_STREAMING(PlayerQuiEventStreaming.class);


    private final static Map<Class<? extends Streaming>, StreamingType> CLASS_STREAMING = new HashMap<>();
    private final static Map<String, StreamingType> STRING_STREAMING = new HashMap<>();
    static {
        for (StreamingType streamingType : StreamingType.values()) {
            CLASS_STREAMING.put(streamingType.getStreamingClass(), streamingType);
            STRING_STREAMING.put(streamingType.name(), streamingType);
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
        return Optional.ofNullable(CLASS_STREAMING.get(aClass));
    }

    public static Optional<StreamingType> getStreamingTypeByName(String name){
        return Optional.ofNullable(STRING_STREAMING.get(name));
    }
}
