package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.DoubleBinaryTag;

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
