package eu.mshadeproduction.mwork;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.json.JSONObject;

import java.io.IOException;

public class ReceiverSerializer extends JsonSerializer<Receiver> {

    @Override
    public void serialize(Receiver value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("receiverType", value.getReceiverType().name());
        if (value.getReceiver().isPresent()) gen.writeStringField("receiverType", value.getReceiverType().name());

        gen.writeEndObject();
    }
}
