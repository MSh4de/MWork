package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.FloatBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FloatBinaryTagBuffer implements NameTagBuffer<FloatBinaryTag> {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, FloatBinaryTag floatBinaryTag) throws Exception {
        outputStream.writeFloat(floatBinaryTag.getValue());
    }

    @Override
    public FloatBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new FloatBinaryTag(inputStream.readFloat());
    }
}
