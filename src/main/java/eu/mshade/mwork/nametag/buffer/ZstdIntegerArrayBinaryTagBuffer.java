package eu.mshade.mwork.nametag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagBuffer;
import eu.mshade.mwork.nametag.NameTagBufferDriver;
import eu.mshade.mwork.nametag.entity.IntegerArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdIntegerArrayBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
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
    public BinaryTag<?> read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
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
