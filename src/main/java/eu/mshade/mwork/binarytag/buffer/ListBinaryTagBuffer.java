package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ListBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        ListBinaryTag listBinaryTag = (ListBinaryTag) binaryTag;
        BinaryTagType tagType = listBinaryTag.getElementType();
        outputStream.writeByte(tagType.getType());
        outputStream.writeInt(listBinaryTag.size());
        BinaryTagBuffer bufferByType = binaryTagBufferDriver.getBufferByType(tagType);
        for (BinaryTag<?> tag : listBinaryTag.getValue()) {
            bufferByType.write(binaryTagBufferDriver, outputStream, tag);
        }
    }

    @Override
    public ListBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        BinaryTagType tagType = BinaryTagType.getTagTypeById(inputStream.readByte());
        ListBinaryTag listBinaryTag = new ListBinaryTag(tagType);
        BinaryTagBuffer binaryTagBuffer = binaryTagBufferDriver.getBufferByType(BinaryTagType.getTagTypeById(inputStream.readByte()));
        int length = inputStream.readInt();
        for (int i = 0; i < length; i++) {
            listBinaryTag.add(binaryTagBuffer.read(binaryTagBufferDriver, inputStream));
        }
        return listBinaryTag;
    }
}
