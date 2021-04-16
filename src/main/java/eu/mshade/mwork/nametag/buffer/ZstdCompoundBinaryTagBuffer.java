package eu.mshade.mwork.nametag.v2.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;
import eu.mshade.mwork.nametag.v2.NameTagBuffer;
import eu.mshade.mwork.nametag.v2.NameTagBufferDriver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdCompoundBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        nameTagBufferDriver.getBufferByType(BinaryTagType.COMPOUND).write(nameTagBufferDriver, dataOutputStream, binaryTag);
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
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(Zstd.decompress(payload, realSize)));
        return nameTagBufferDriver.getBufferByType(BinaryTagType.COMPOUND).read(nameTagBufferDriver, dataInputStream);
    }
}
