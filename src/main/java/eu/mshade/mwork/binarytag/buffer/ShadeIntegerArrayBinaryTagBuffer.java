package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class ShadeIntegerArrayBinaryTagBuffer extends IntegerArrayBinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {

        Consumer<DataOutputStream> consumer = dataOutputStream -> {
            try {
                super.write(binaryTagDriver, dataOutputStream, binaryTag);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        super.writeShade(outputStream, consumer);
    }

    @Override
    public IntegerArrayBinaryTag read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {

        Function<DataInputStream, BinaryTag<?>> function = dataInputStream -> {
            try {
                return super.read(binaryTagDriver, dataInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        return (IntegerArrayBinaryTag) super.readShade(inputStream, function);
    }
}
