package eu.mshade.mwork;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class MWork {

    private static MWork mWork;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private MWork() {
        mWork = this;
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(JSONObject.class, new JSONObjectSerializer());
        simpleModule.addDeserializer(JSONObject.class, new JSONObjectDeserializer());
        this.objectMapper.registerModule(simpleModule);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
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

}
