package eu.mshade.mwork.nametag.v2.buffer;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;
import eu.mshade.mwork.nametag.v2.entity.IntegerBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class IntegerBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        IntegerBinaryTag integerBinaryTag = (IntegerBinaryTag) binaryTag;
        outputStream.writeInt(integerBinaryTag.getValue());
    }

    @Override
    public IntegerBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        return new IntegerBinaryTag(inputStream.readInt());
    }
}
