package eu.mshade.mwork.nametag.buffer;

import eu.mshade.mwork.nametag.BinaryTag;
import eu.mshade.mwork.nametag.BinaryTagType;
import eu.mshade.mwork.nametag.NameTagBuffer;
import eu.mshade.mwork.nametag.NameTagBufferDriver;
import eu.mshade.mwork.nametag.entity.ListBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ListBinaryTagBuffer implements NameTagBuffer {

    @Override
    public void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ListBinaryTag<BinaryTag<?>> listBinaryTag = (ListBinaryTag<BinaryTag<?>>) binaryTag;
        BinaryTagType tagType = BinaryTagType.getTagTypeByClass(listBinaryTag.getElementType());
        outputStream.writeByte(tagType.getId());
        outputStream.writeInt(listBinaryTag.getValue().size());
        NameTagBuffer bufferByType = nameTagBufferDriver.getBufferByType(tagType);
        for (BinaryTag<?> tag : listBinaryTag.getValue()) {
            bufferByType.write(nameTagBufferDriver, outputStream, tag);
        }
    }

    @Override
    public ListBinaryTag<BinaryTag<?>> read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception {
        BinaryTagType tagType = BinaryTagType.getTagTypeById(inputStream.readByte());
        ListBinaryTag<BinaryTag<?>> listBinaryTag = new ListBinaryTag(tagType.getClazz());
        NameTagBuffer nameTagBuffer = nameTagBufferDriver.getBufferByType(BinaryTagType.getTagTypeById(inputStream.readByte()));
        int length = inputStream.readInt();
        for (int i = 0; i < length; i++) {
            listBinaryTag.getValue().add(nameTagBuffer.read(nameTagBufferDriver, inputStream));
        }
        return listBinaryTag;
    }
}
