package eu.mshade.mwork;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.UUID;

public final class MWork {


    private static MWork mWork;
    private static Unsafe UNSAFE;
    private static Logger LOGGER = LoggerFactory.getLogger(MWork.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BinaryTagDriver binaryTagDriver = new BinaryTagDriver();

    private MWork() {

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        mWork = this;
        this.binaryTagDriver.registerMarshal(UUID.class, new UUIDBinaryTagMarshal());

        SimpleModule simpleModule = new SimpleModule();
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        simpleModule.addSerializer(JSONObject.class, new JSONObjectSerializer());
        simpleModule.addDeserializer(JSONObject.class, new JSONObjectDeserializer());
        this.objectMapper.registerModule(simpleModule);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
    }

    public BinaryTagDriver getBinaryTagDriver() {
        return binaryTagDriver;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static MWork get(){
        return (mWork != null ? mWork : new MWork());
    }

    public Unsafe getUnsafe() {
        return UNSAFE;
    }
}
