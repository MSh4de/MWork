package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.ByteBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ByteBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        ByteBinaryTag byteBinaryTag = (ByteBinaryTag) binaryTag;
        outputStream.writeByte(byteBinaryTag.getValue());
    }

    @Override
    public ByteBinaryTag read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
        return new ByteBinaryTag(inputStream.readByte());
    }
}
