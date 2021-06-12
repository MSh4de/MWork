package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.EndBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CompoundBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        CompoundBinaryTag compoundBinaryTag = (CompoundBinaryTag) binaryTag;
        for (Map.Entry<String, BinaryTag<?>> entry : compoundBinaryTag.getValue().entrySet()) {
            byte[] name = entry.getKey().getBytes(StandardCharsets.UTF_8);
            outputStream.writeByte(entry.getValue().getType().getType());
            outputStream.writeShort(name.length);
            outputStream.write(name);
            binaryTagBufferDriver.getBufferByType(entry.getValue().getType()).write(binaryTagBufferDriver, outputStream, entry.getValue());
        }
        outputStream.write(EndBinaryTag.TAG.getValue());
    }

    @Override
    public CompoundBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        BinaryTagType tagTypeById;
        do {
            byte b = inputStream.readByte();
            tagTypeById = BinaryTagType.getTagTypeById(b);
            if (tagTypeById != BinaryTagType.END) {
                short length = inputStream.readShort();
                byte[] bytes = new byte[length];
                inputStream.readFully(bytes);
                String name = new String(bytes, StandardCharsets.UTF_8);
                compoundBinaryTag.getValue().put(name, binaryTagBufferDriver.getBufferByType(tagTypeById).read(binaryTagBufferDriver, inputStream));
            }
        } while (tagTypeById != BinaryTagType.END);
        return compoundBinaryTag;
    }
}
