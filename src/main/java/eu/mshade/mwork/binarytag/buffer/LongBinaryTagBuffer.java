package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.LongBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LongBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        LongBinaryTag longBinaryTag = (LongBinaryTag) binaryTag;
        outputStream.writeLong(longBinaryTag.getValue());
    }

    @Override
    public LongBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new LongBinaryTag(inputStream.readLong());
    }
}
