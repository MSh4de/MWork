package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.FloatBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FloatBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        FloatBinaryTag floatBinaryTag = (FloatBinaryTag) binaryTag;
        outputStream.writeFloat(floatBinaryTag.getValue());
    }

    @Override
    public FloatBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new FloatBinaryTag(inputStream.readFloat());
    }
}
