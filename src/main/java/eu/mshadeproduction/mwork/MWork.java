package eu.mshadeproduction.mwork;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class MWork {

    private static MWork mWork;
    private final Set<Class<?>> primitives = new HashSet<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PacketDriver packetDriver = new DefaultPacketDriver();
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private MWork() {
        mWork = this;
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(JSONObject.class, new JSONObjectSerializer());
        simpleModule.addDeserializer(JSONObject.class, new JSONObjectDeserializer());
        simpleModule.addSerializer(Receiver.class, new ReceiverSerializer());
        simpleModule.addDeserializer(Receiver.class, new ReceiverDeserializer());
        this.objectMapper.registerModule(simpleModule);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);


        primitives.add(String.class);
        primitives.add(int.class);
        primitives.add(Integer.class);
        primitives.add(boolean.class);
        primitives.add(Boolean.class);
        primitives.add(Float.class);
        primitives.add(float.class);
        primitives.add(Character.class);
        primitives.add(char.class);
        primitives.add(Byte.class);
        primitives.add(byte.class);
        primitives.add(Long.class);
        primitives.add(long.class);
        primitives.add(Double.class);
        primitives.add(double.class);

    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public static MWork get(){
        return (mWork != null ? mWork : new MWork());
    }

    public static void runAsync(Runnable runnable){
        MWork.get().getScheduledExecutorService().execute(runnable);
    }

    public PacketDriver getPacketDriver() {
        return packetDriver;
    }

    public String serialize(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    public <T> T deserialize(String s, Class<T> aClass){
            try {
                return objectMapper.readValue(s, aClass);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e.getCause());
            }
    }

    public boolean isPrimitive(Class<?> aClass){
        return primitives.contains(aClass);
    }
}
