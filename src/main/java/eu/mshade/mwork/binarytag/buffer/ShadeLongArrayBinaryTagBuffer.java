package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShadeLongArrayBinaryTagBuffer extends LongArrayBinaryTagBuffer{

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        super.write(binaryTagBufferDriver, dataOutputStream, binaryTag);
        outputStream.writeInt(byteArrayOutputStream.size());
        outputStream.write(byteArrayOutputStream.toByteArray());
        dataOutputStream.close();
    }

    @Override
    public LongArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int size = inputStream.readInt();
        byte[] payload = new byte[size];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return super.read(binaryTagBufferDriver, dataInputStream).toShade();
    }
}
