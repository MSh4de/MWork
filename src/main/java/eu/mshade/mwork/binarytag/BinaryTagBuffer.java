package eu.mshade.mwork.binarytag;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.MConsumer;
import eu.mshade.mwork.MFunction;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Function;

public interface BinaryTagBuffer {

    void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException;

    BinaryTag<?> read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException;


    default void writeShade(DataOutputStream outputStream, Consumer<DataOutputStream> consumer) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        consumer.accept(dataOutputStream);
        outputStream.writeInt(byteArrayOutputStream.size());
        outputStream.write(byteArrayOutputStream.toByteArray());
        dataOutputStream.close();
    }
    
    default BinaryTag<?> readShade(DataInputStream inputStream, Function<DataInputStream, BinaryTag<?>> consumer) throws IOException {
        int size = inputStream.readInt();
        byte[] payload = new byte[size];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return ((ShadeBinaryTag<?>) consumer.apply(dataInputStream)).toShade();
    }

    default void writeZstd(DataOutputStream outputStream, Consumer<DataOutputStream> consumer) throws IOException {
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

    default BinaryTag<?> readZstd(DataInputStream inputStream, Function<DataInputStream, BinaryTag<?>> consumer) throws IOException {
        int realLength = inputStream.readInt();
        int length = inputStream.readInt();
        byte[] payload = new byte[length];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Zstd.decompress(payload, realLength));
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return ((ZstdBinaryTag<?>) consumer.apply(dataInputStream)).toZstd();
    }
}
