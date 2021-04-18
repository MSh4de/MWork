package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.StringBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

public class StringBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        StringBinaryTag stringBinaryTag = (StringBinaryTag) binaryTag;
        byte[] bytes = stringBinaryTag.getValue().getBytes(StandardCharsets.UTF_8);
        outputStream.writeShort(bytes.length);
        outputStream.write(bytes);
    }

    @Override
    public StringBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        short length = inputStream.readShort();
        byte[] bytes = new byte[length];
        inputStream.readFully(bytes);
        return new StringBinaryTag(new String(bytes, StandardCharsets.UTF_8));
    }
}
