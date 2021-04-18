package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.DoubleBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class DoubleBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        DoubleBinaryTag doubleBinaryTag = (DoubleBinaryTag) binaryTag;
        outputStream.writeDouble(doubleBinaryTag.getValue());
    }

    @Override
    public DoubleBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new DoubleBinaryTag(inputStream.readDouble());
    }
}
