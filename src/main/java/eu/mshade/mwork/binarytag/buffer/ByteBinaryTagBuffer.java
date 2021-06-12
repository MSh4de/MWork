package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.ByteBinaryTag;

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
