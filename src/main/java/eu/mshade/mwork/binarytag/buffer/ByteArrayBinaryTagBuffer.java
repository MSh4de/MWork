package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.ByteArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ByteArrayBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayBinaryTag byteArrayBinaryTag = (ByteArrayBinaryTag) binaryTag;
        outputStream.writeInt(byteArrayBinaryTag.getValue().length);
        outputStream.write(byteArrayBinaryTag.getValue());

    }

    @Override
    public ByteArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int length = inputStream.readInt();
        byte[] bytes = new byte[length];
        inputStream.readFully(bytes);
        return new ByteArrayBinaryTag(bytes);
    }
}
