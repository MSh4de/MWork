package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;

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
