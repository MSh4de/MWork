package eu.mshadeproduction.mwork;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;

import java.io.IOException;

public class ReceiverDeserializer extends JsonDeserializer<Receiver>  {
    @Override
    public Receiver deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        JSONObject jsonObject = new JSONObject(node.toString());
        MOptional<String> stringMOptional = MOptional.empty();
        if (jsonObject.has("receiver")) {
            stringMOptional = MOptional.of(jsonObject.getString("receiver"));
        }
        return new Receiver(stringMOptional, jsonObject.getEnum(ReceiverType.class, "receiverType"));
    }
}
