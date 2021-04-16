package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.DoubleBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class DoubleBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        DoubleBinaryTag doubleBinaryTag = (DoubleBinaryTag) binaryTag;
        outputStream.writeDouble(doubleBinaryTag.getValue());
    }

    @Override
    public DoubleBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new DoubleBinaryTag(inputStream.readDouble());
    }
}
