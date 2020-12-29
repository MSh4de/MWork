package eu.mshadeproduction.mwork.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class TradeBucketDeserializer extends JsonDeserializer<TradeContext> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TradeBucketDeserializer() {
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public TradeContext deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        TradeContextType bodyType = TradeContextType.valueOf(node.get("objectContext").asText());
        if (bodyType == TradeContextType.STRING){
            return new TradeContext(bodyType, node.get("object").asText());
        }else if (bodyType == TradeContextType.NUMBER){
            return new TradeContext(bodyType, node.get("object").asDouble());
        }
        return new TradeContext(bodyType, objectMapper.readValue(node.get("object").toString(), bodyType.getBodyClass()));
    }
}
