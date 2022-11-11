package eu.mshade.mwork;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public final class MWork {


    private static MWork mWork;
    private static Logger LOGGER = LoggerFactory.getLogger(MWork.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BinaryTagDriver binaryTagDriver = new BinaryTagDriver();

    private MWork() {

        mWork = this;
        this.binaryTagDriver.registerMarshal(UUID.class, new UUIDBinaryTagMarshal());

        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
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
}
