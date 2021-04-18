package eu.mshade.mwork.binarytag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdLongArrayBinaryBuffer implements BinaryTagBuffer {
    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        LongArrayBinaryTag longArrayBinaryTag = (LongArrayBinaryTag) binaryTag;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (long i : longArrayBinaryTag.getValue()) {
            dataOutputStream.writeLong(i);
        }
        byte[] compress = Zstd.compress(byteArrayOutputStream.toByteArray());
        outputStream.writeInt(byteArrayOutputStream.size());
        outputStream.writeInt(compress.length);
        outputStream.write(compress);
        dataOutputStream.close();
    }

    @Override
    public BinaryTag<?> read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int realSize = inputStream.readInt();
        int compressSize = inputStream.readInt();
        byte[] payload = new byte[compressSize];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Zstd.decompress(payload, realSize));
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        long[] longs = new long[realSize];
        for (int i = 0; i < realSize; i++) {
            longs[i] = dataInputStream.readLong();
        }
        dataInputStream.close();
        return new LongArrayBinaryTag(longs);
    }
}
