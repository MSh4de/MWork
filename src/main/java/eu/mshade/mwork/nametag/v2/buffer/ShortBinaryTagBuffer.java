package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.ShortBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShortBinaryTagBuffer implements NameTagBuffer<ShortBinaryTag> {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, ShortBinaryTag shortBinaryTag) throws Exception {
        outputStream.writeShort(shortBinaryTag.getValue());
    }

    @Override
    public ShortBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new ShortBinaryTag(inputStream.readShort());
    }
}
