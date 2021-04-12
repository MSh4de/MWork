package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.LongBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LongBinaryTagBuffer implements NameTagBuffer<LongBinaryTag> {


    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, LongBinaryTag longBinaryTag) throws Exception {
        outputStream.writeLong(longBinaryTag.getValue());
    }

    @Override
    public LongBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new LongBinaryTag(inputStream.readLong());
    }
}
