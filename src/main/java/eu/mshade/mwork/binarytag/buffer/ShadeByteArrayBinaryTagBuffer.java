package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.ByteArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShadeByteArrayBinaryTagBuffer extends ByteArrayBinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        super.writeShade(outputStream, dataOutputStream -> super.write(binaryTagBufferDriver, dataOutputStream, binaryTag));
    }

    @Override
    public ByteArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return (ByteArrayBinaryTag) super.readShade(inputStream, dataInputStream -> super.read(binaryTagBufferDriver, dataInputStream));
    }
}
