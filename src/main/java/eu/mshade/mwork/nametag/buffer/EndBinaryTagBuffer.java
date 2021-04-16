package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.EndBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class EndBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> endBinaryTag) throws Exception {
        outputStream.writeByte(0);
    }

    @Override
    public EndBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        inputStream.readByte();
        return new EndBinaryTag();
    }
}
