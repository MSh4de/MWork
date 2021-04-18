package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.ByteBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ByteBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteBinaryTag byteBinaryTag = (ByteBinaryTag) binaryTag;
        outputStream.writeByte(byteBinaryTag.getValue());
    }

    @Override
    public ByteBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new ByteBinaryTag(inputStream.readByte());
    }
}
