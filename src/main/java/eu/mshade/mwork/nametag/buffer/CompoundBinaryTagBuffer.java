package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;
import eu.mshade.mwork.nametag.NameTagBuffer;
import eu.mshade.mwork.nametag.NameTagBufferDriver;
import eu.mshade.mwork.nametag.entity.CompoundBinaryTag;
import eu.mshade.mwork.nametag.entity.EndBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CompoundBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        CompoundBinaryTag compoundBinaryTag = (CompoundBinaryTag) binaryTag;
        for (Map.Entry<String, BinaryTag<?>> entry : compoundBinaryTag.getValue().entrySet()) {
            byte[] name = entry.getKey().getBytes(StandardCharsets.UTF_8);
            outputStream.writeByte(entry.getValue().getType().getId());
            outputStream.writeShort(name.length);
            outputStream.write(name);
            nameTagBufferDriver.getBufferByType(entry.getValue().getType()).write(nameTagBufferDriver, outputStream, entry.getValue());
        }
        outputStream.write(EndBinaryTag.TAG.getValue());
    }

    @Override
    public CompoundBinaryTag read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        BinaryTagType tagTypeById;
        do {
            byte b = inputStream.readByte();
            tagTypeById = BinaryTagType.getTagTypeById(b);
            short length = inputStream.readShort();
            byte[] bytes = new byte[length];
            inputStream.readFully(bytes);
            String name = new String(bytes, StandardCharsets.UTF_8);
            compoundBinaryTag.getValue().put(name, nameTagBufferDriver.getBufferByType(tagTypeById).read(nameTagBufferDriver, inputStream));
        } while (tagTypeById != BinaryTagType.END);
        return compoundBinaryTag;
    }
}
