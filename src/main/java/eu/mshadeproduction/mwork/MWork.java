package eu.mshadeproduction.mwork;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.mshadeproduction.mwork.service.DefaultServiceDriver;
import eu.mshadeproduction.mwork.service.ServiceDriver;
import eu.mshadeproduction.mwork.service.TradeContext;
import eu.mshadeproduction.mwork.service.TradeBucketDeserializer;
import eu.mshadeproduction.mwork.streaming.DefaultStreamingDriver;
import eu.mshadeproduction.mwork.streaming.StreamingDriver;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class MWork {

    private static MWork mWork;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ServiceDriver serviceDriver = new DefaultServiceDriver();
    private final StreamingDriver streamingDriver = new DefaultStreamingDriver();
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    private MWork() {
        mWork = this;
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(TradeContext.class, new TradeBucketDeserializer());
        this.objectMapper.registerModule(simpleModule);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
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

    public ServiceDriver getServiceDriver() {
        return serviceDriver;
    }

    public StreamingDriver getStreamingDriver() {
        return streamingDriver;
    }

    public CompletableFuture<String> serialize(Object object){
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        scheduledExecutorService.execute(() -> {
            try {
                completableFuture.complete(objectMapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return completableFuture;
    }

    public <T> CompletableFuture<T> deserialize(String s, Class<T> aClass){
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        scheduledExecutorService.execute(() -> {
            try {
                completableFuture.complete(objectMapper.readValue(s, aClass));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return completableFuture;
    }
}
