package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.ByteArrayBinaryTag;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShadeLongArrayBinaryTagBuffer extends LongArrayBinaryTagBuffer{

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        super.writeShade(binaryTagBufferDriver, outputStream, binaryTag);
    }

    @Override
    public LongArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return (LongArrayBinaryTag) super.readShade(binaryTagBufferDriver, inputStream);
    }
}
