package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.ShortBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShortBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ShortBinaryTag shortBinaryTag = (ShortBinaryTag) binaryTag;
        outputStream.writeShort(shortBinaryTag.getValue());
    }

    @Override
    public ShortBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new ShortBinaryTag(inputStream.readShort());
    }
}
