package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.EndBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class EndBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> endBinaryTag) throws Exception {
        outputStream.writeByte(0);
    }

    @Override
    public EndBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        inputStream.readByte();
        return new EndBinaryTag();
    }
}
