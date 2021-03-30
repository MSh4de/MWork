package eu.mshade.mwork;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.json.JSONObject;

import java.io.IOException;

public class JSONObjectSerializer extends JsonSerializer<JSONObject> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void serialize(JSONObject value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String s = objectMapper.writeValueAsString(value.toMap());
        String result = s.substring(1, s.length() - 1);
        gen.writeStartObject();
        gen.writeRaw(result);
        gen.writeEndObject();
    }
}
