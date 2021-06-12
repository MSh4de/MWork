package eu.mshade.mwork;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.DefaultBinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.DefaultBinaryTagMarshal;
import eu.mshade.mwork.binarytag.marshal.BinaryTagMarshal;
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
    private final BinaryTagBufferDriver binaryTagBufferDriver = new DefaultBinaryTagBufferDriver();
    private final BinaryTagMarshal binaryTagMarshal = new DefaultBinaryTagMarshal();

    private MWork() {

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        mWork = this;
        this.binaryTagMarshal.registerAdaptor(UUID.class, new UUIDBinaryTagMarshalBuffer());

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(JSONObject.class, new JSONObjectSerializer());
        simpleModule.addDeserializer(JSONObject.class, new JSONObjectDeserializer());
        this.objectMapper.registerModule(simpleModule);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
    }

    public BinaryTagBufferDriver getBinaryTagBufferDriver() {
        return binaryTagBufferDriver;
    }

    public BinaryTagMarshal getBinaryTagMarshal() {
        return binaryTagMarshal;
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
