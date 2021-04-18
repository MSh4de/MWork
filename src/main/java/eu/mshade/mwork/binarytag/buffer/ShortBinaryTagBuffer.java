package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.ShortBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShortBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ShortBinaryTag shortBinaryTag = (ShortBinaryTag) binaryTag;
        outputStream.writeShort(shortBinaryTag.getValue());
    }

    @Override
    public ShortBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new ShortBinaryTag(inputStream.readShort());
    }
}
