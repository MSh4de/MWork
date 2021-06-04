package eu.mshade.mwork.binarytag;

import com.github.luben.zstd.Zstd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface BinaryTagBuffer {

    void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception;

    BinaryTag<?> read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception;


    default void writeShade(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        this.write(binaryTagBufferDriver, dataOutputStream, binaryTag);
        outputStream.writeInt(byteArrayOutputStream.size());
        outputStream.write(byteArrayOutputStream.toByteArray());
        dataOutputStream.close();
    }
    
    default BinaryTag<?> readShade(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int size = inputStream.readInt();
        byte[] payload = new byte[size];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return ((ShadeBinaryTag<?>) this.read(binaryTagBufferDriver, dataInputStream)).toShade();
    }

    default void writeZstd(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        this.write(binaryTagBufferDriver, dataOutputStream, binaryTag);
        byte[] compress = Zstd.compress(byteArrayOutputStream.toByteArray());
        outputStream.writeInt(dataOutputStream.size());
        outputStream.writeInt(compress.length);
        outputStream.write(compress);
        dataOutputStream.close();
        byteArrayOutputStream.close();
    }

    default BinaryTag<?> readZstd(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int realLength = inputStream.readInt();
        int length = inputStream.readInt();
        byte[] payload = new byte[length];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Zstd.decompress(payload, realLength));
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return ((ZstdBinaryTag<?>) this.read(binaryTagBufferDriver, dataInputStream)).toZstd();
    }
}
