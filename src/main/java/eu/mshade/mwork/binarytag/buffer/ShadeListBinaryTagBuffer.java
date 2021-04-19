package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShadeListBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        binaryTagBufferDriver.getBufferByType(BinaryTagType.LIST).write(binaryTagBufferDriver, dataOutputStream, binaryTag);
        outputStream.writeInt(byteArrayOutputStream.size());
        outputStream.write(byteArrayOutputStream.toByteArray());
        dataOutputStream.close();
    }

    @Override
    public BinaryTag<?> read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        int size = inputStream.readInt();
        byte[] payload = new byte[size];
        inputStream.readFully(payload);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        return ((ListBinaryTag) binaryTagBufferDriver.getBufferByType(BinaryTagType.LIST).read(binaryTagBufferDriver, dataInputStream)).toShade();
    }

}
