package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.EndBinaryTag;

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
        return EndBinaryTag.TAG;
    }
}
