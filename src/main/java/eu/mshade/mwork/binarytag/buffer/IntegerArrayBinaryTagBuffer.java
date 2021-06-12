package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class IntegerArrayBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        IntegerArrayBinaryTag integerArrayBinaryTag = (IntegerArrayBinaryTag) binaryTag;
        int[] ints = integerArrayBinaryTag.getValue();
        outputStream.writeInt(ints.length);
        for (int anInt : ints) {
            outputStream.writeInt(anInt);
        }
    }

    @Override
    public IntegerArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int length = inputStream.readInt();
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = inputStream.readInt();
        }
        return new IntegerArrayBinaryTag(ints);
    }
}
