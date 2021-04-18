package eu.mshade.mwork.binarytag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdIntegerArrayBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        IntegerArrayBinaryTag integerArrayBinaryTag = (IntegerArrayBinaryTag) binaryTag;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i : integerArrayBinaryTag.getValue()) {
            dataOutputStream.writeInt(i);
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
        int[] ints = new int[realSize];
        for (int i = 0; i < realSize; i++) {
            ints[i] = dataInputStream.readInt();
        }
        dataInputStream.close();
        return new IntegerArrayBinaryTag(ints);
    }
}
