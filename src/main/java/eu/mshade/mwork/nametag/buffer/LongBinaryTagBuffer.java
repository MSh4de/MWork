package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.LongBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LongBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        LongBinaryTag longBinaryTag = (LongBinaryTag) binaryTag;
        outputStream.writeLong(longBinaryTag.getValue());
    }

    @Override
    public LongBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new LongBinaryTag(inputStream.readLong());
    }
}
