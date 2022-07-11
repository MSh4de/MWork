package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.EndBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CompoundBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        CompoundBinaryTag compoundBinaryTag = (CompoundBinaryTag) binaryTag;
        for (Map.Entry<String, BinaryTag<?>> entry : compoundBinaryTag.getValue().entrySet()) {
            byte[] name = entry.getKey().getBytes(StandardCharsets.UTF_8);
            outputStream.writeByte(entry.getValue().getType().getType());
            outputStream.writeShort(name.length);
            outputStream.write(name);
            binaryTagDriver.getBufferByType(entry.getValue().getType()).write(binaryTagDriver, outputStream, entry.getValue());
        }
        outputStream.write(EndBinaryTag.TAG.getValue());
    }

    @Override
    public CompoundBinaryTag read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
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
                compoundBinaryTag.getValue().put(name, binaryTagDriver.getBufferByType(tagTypeById).read(binaryTagDriver, inputStream));
            }
        } while (tagTypeById != BinaryTagType.END);
        return compoundBinaryTag;
    }
}
