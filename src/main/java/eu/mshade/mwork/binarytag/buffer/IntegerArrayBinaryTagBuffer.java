package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IntegerArrayBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        IntegerArrayBinaryTag integerArrayBinaryTag = (IntegerArrayBinaryTag) binaryTag;
        int[] ints = integerArrayBinaryTag.getValue();
        outputStream.writeInt(ints.length);
        for (int anInt : ints) {
            outputStream.writeInt(anInt);
        }
    }

    @Override
    public IntegerArrayBinaryTag read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
        int length = inputStream.readInt();
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = inputStream.readInt();
        }
        return new IntegerArrayBinaryTag(ints);
    }
}
