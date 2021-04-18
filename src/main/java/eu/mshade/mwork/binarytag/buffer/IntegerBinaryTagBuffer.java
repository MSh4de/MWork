package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.IntegerBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class IntegerBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        IntegerBinaryTag integerBinaryTag = (IntegerBinaryTag) binaryTag;
        outputStream.writeInt(integerBinaryTag.getValue());
    }

    @Override
    public IntegerBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new IntegerBinaryTag(inputStream.readInt());
    }
}
