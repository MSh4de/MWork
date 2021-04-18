package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.LongBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LongBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        LongBinaryTag longBinaryTag = (LongBinaryTag) binaryTag;
        outputStream.writeLong(longBinaryTag.getValue());
    }

    @Override
    public LongBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new LongBinaryTag(inputStream.readLong());
    }
}
