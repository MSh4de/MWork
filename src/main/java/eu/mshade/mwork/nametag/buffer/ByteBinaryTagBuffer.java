package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagBuffer;
import eu.mshade.mwork.nametag.NameTagBufferDriver;
import eu.mshade.mwork.nametag.entity.ByteBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ByteBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteBinaryTag byteBinaryTag = (ByteBinaryTag) binaryTag;
        outputStream.writeByte(byteBinaryTag.getValue());
    }

    @Override
    public ByteBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new ByteBinaryTag(inputStream.readByte());
    }
}
