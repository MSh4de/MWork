package eu.mshadeproduction.mwork;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;

import java.io.IOException;

public class JSONObjectDeserializer extends JsonDeserializer<JSONObject> {
    @Override
    public JSONObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return new JSONObject(node.toString());
    }
}
