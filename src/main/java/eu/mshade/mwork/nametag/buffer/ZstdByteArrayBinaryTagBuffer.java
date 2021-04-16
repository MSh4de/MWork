package eu.mshade.mwork.nametag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.NameTagBuffer;
import eu.mshade.mwork.nametag.NameTagBufferDriver;
import eu.mshade.mwork.nametag.entity.ByteArrayBinaryTag;
import eu.mshade.mwork.nametag.entity.ZstdByteArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdByteArrayBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayBinaryTag byteArrayBinaryTag = (ByteArrayBinaryTag) binaryTag;
        byte[] compress = Zstd.compress(byteArrayBinaryTag.getValue());
        outputStream.writeInt(byteArrayBinaryTag.getValue().length);
        outputStream.writeInt(compress.length);
        outputStream.write(compress);
    }

    @Override
    public BinaryTag<?> read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        int realLength = inputStream.readInt();
        int length = inputStream.readInt();
        byte[] bytes = new byte[length];
        inputStream.readFully(bytes);
        return new ZstdByteArrayBinaryTag(Zstd.decompress(bytes, realLength));
    }
}
