package eu.mshade.mwork.binarytag;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.MConsumer;
import eu.mshade.mwork.MFunction;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.function.Consumer;

public interface BinaryTagBuffer {

    void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception;

    BinaryTag<?> read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception;


    default void writeShade(DataOutputStream outputStream, MConsumer<DataOutputStream> consumer) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        consumer.accept(dataOutputStream);
        outputStream.writeInt(byteArrayOutputStream.size());
        outputStream.write(byteArrayOutputStream.toByteArray());
        dataOutputStream.close();
    }
    
    default BinaryTag<?> readShade( DataInputStream inputStream, MFunction<DataInputStream, BinaryTag<?>> consumer) throws Exception {
        int size = inputStream.readInt();
        byte[] payload = new byte[size];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return ((ShadeBinaryTag<?>) consumer.apply(dataInputStream)).toShade();
    }

    default void writeZstd(DataOutputStream outputStream, MConsumer<DataOutputStream> consumer) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        consumer.accept(dataOutputStream);
        byte[] compress = Zstd.compress(byteArrayOutputStream.toByteArray());
        outputStream.writeInt(dataOutputStream.size());
        outputStream.writeInt(compress.length);
        outputStream.write(compress);
        dataOutputStream.close();
        byteArrayOutputStream.close();
    }

    default BinaryTag<?> readZstd(DataInputStream inputStream, MFunction<DataInputStream, BinaryTag<?>> consumer) throws Exception {
        int realLength = inputStream.readInt();
        int length = inputStream.readInt();
        byte[] payload = new byte[length];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Zstd.decompress(payload, realLength));
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return ((ZstdBinaryTag<?>) consumer.apply(dataInputStream)).toZstd();
    }
}
