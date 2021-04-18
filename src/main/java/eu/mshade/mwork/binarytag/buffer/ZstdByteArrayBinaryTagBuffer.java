package eu.mshade.mwork.binarytag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.ByteArrayBinaryTag;
import eu.mshade.mwork.binarytag.entity.ZstdByteArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdByteArrayBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayBinaryTag byteArrayBinaryTag = (ByteArrayBinaryTag) binaryTag;
        byte[] compress = Zstd.compress(byteArrayBinaryTag.getValue());
        outputStream.writeInt(byteArrayBinaryTag.getValue().length);
        outputStream.writeInt(compress.length);
        outputStream.write(compress);
    }

    @Override
    public BinaryTag<?> read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int realLength = inputStream.readInt();
        int length = inputStream.readInt();
        byte[] bytes = new byte[length];
        inputStream.readFully(bytes);
        return new ZstdByteArrayBinaryTag(Zstd.decompress(bytes, realLength));
    }
}
