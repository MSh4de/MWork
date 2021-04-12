package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.ListBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ListBinaryTagBuffer implements NameTagBuffer<ListBinaryTag> {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, ListBinaryTag listBinaryTag) throws Exception {

    }

    @Override
    public ListBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return null;
    }
}
