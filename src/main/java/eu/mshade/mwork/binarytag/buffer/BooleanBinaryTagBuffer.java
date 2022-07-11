package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.BooleanBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BooleanBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        outputStream.writeBoolean((boolean) binaryTag.getValue());
    }

    @Override
    public BinaryTag<?> read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
        return new BooleanBinaryTag(inputStream.readBoolean());
    }
}
