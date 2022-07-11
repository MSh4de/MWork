package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.ShortBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ShortBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        ShortBinaryTag shortBinaryTag = (ShortBinaryTag) binaryTag;
        outputStream.writeShort(shortBinaryTag.getValue());
    }

    @Override
    public ShortBinaryTag read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
        return new ShortBinaryTag(inputStream.readShort());
    }
}
