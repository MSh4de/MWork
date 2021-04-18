package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.FloatBinaryTag;

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
