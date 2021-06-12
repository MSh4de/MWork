package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LongArrayBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        LongArrayBinaryTag longArrayBinaryTag = (LongArrayBinaryTag) binaryTag;
        long[] longs = longArrayBinaryTag.getValue();
        outputStream.writeInt(longs.length);
        for (long l : longs) {
            outputStream.writeLong(l);
        }
    }

    @Override
    public LongArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int length = inputStream.readInt();
        long[] longs = new long[length];
        for (int i = 0; i < length; i++) {
            longs[i] = inputStream.readLong();
        }
        return new LongArrayBinaryTag(longs);
    }

}
