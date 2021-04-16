package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.ByteBinaryTag;

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
