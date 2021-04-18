package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;
import eu.mshade.mwork.nametag.BinaryTagBuffer;
import eu.mshade.mwork.nametag.BinaryTagBufferDriver;
import eu.mshade.mwork.nametag.entity.CompoundBinaryTag;
import eu.mshade.mwork.nametag.entity.EndBinaryTag;

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
                System.out.println(tagTypeById);
                compoundBinaryTag.getValue().put(name, binaryTagBufferDriver.getBufferByType(tagTypeById).read(binaryTagBufferDriver, inputStream));
            }
        } while (tagTypeById != BinaryTagType.END);
        return compoundBinaryTag;
    }
}
