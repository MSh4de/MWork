package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagBuffer;
import eu.mshade.mwork.nametag.NameTagBufferDriver;
import eu.mshade.mwork.nametag.entity.FloatBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FloatBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        FloatBinaryTag floatBinaryTag = (FloatBinaryTag) binaryTag;
        outputStream.writeFloat(floatBinaryTag.getValue());
    }

    @Override
    public FloatBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new FloatBinaryTag(inputStream.readFloat());
    }
}
