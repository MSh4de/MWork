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
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.UUID;

public final class MWork {

    private static MWork mWork;
    private static Unsafe UNSAFE;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BinaryTagBufferDriver binaryTagBufferDriver = new DefaultBinaryTagBufferDriver();
    private final BinaryTagMarshal binaryTagMarshal = new DefaultBinaryTagMarshal();

    private MWork() {

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mWork = this;
        this.binaryTagMarshal.registerAdaptor(UUID.class, new UUIDBinaryTagMarshalBuffer());

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(JSONObject.class, new JSONObjectSerializer());
        simpleModule.addDeserializer(JSONObject.class, new JSONObjectDeserializer());
        this.objectMapper.registerModule(simpleModule);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        //this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

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

    public Unsafe getUnsafe() {
        return UNSAFE;
    }
}
