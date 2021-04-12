package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.StringBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

public class StringBinaryTagBuffer implements NameTagBuffer<StringBinaryTag> {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, StringBinaryTag stringBinaryTag) throws Exception {
        byte[] bytes = stringBinaryTag.getValue().getBytes(StandardCharsets.UTF_8);
        outputStream.writeShort(bytes.length);
        outputStream.write(bytes);
    }

    @Override
    public StringBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        short length = inputStream.readShort();
        byte[] bytes = new byte[length];
        inputStream.readFully(bytes);
        return new StringBinaryTag(new String(bytes, StandardCharsets.UTF_8));
    }
}
