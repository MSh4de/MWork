package eu.mshade.mwork.binarytag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdCompoundBinaryTagBuffer extends CompoundBinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        super.write(binaryTagBufferDriver, dataOutputStream, binaryTag);
        byte[] compress = Zstd.compress(byteArrayOutputStream.toByteArray());
        outputStream.writeInt(dataOutputStream.size());
        outputStream.writeInt(compress.length);
        outputStream.write(compress);
        outputStream.close();
    }

    @Override
    public CompoundBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int realLength = inputStream.readInt();
        int length = inputStream.readInt();
        byte[] payload = new byte[length];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Zstd.decompress(payload, realLength));
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return super.read(binaryTagBufferDriver, dataInputStream).toZstd();
    }
}
