package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.DoubleBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DoubleBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        DoubleBinaryTag doubleBinaryTag = (DoubleBinaryTag) binaryTag;
        outputStream.writeDouble(doubleBinaryTag.getValue());
    }

    @Override
    public DoubleBinaryTag read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
        return new DoubleBinaryTag(inputStream.readDouble());
    }
}
