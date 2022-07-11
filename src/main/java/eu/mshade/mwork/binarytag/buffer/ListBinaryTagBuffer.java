package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ListBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        ListBinaryTag listBinaryTag = (ListBinaryTag) binaryTag;
        BinaryTagType tagType = listBinaryTag.getElementType();
        outputStream.writeByte(tagType.getType());
        outputStream.writeInt(listBinaryTag.size());
        BinaryTagBuffer bufferByType = binaryTagDriver.getBufferByType(tagType);
        for (BinaryTag<?> tag : listBinaryTag.getValue()) {
            bufferByType.write(binaryTagDriver, outputStream, tag);
        }
    }

    @Override
    public ListBinaryTag read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
        BinaryTagType tagType = BinaryTagType.getTagTypeById(inputStream.readByte());
        ListBinaryTag listBinaryTag = new ListBinaryTag(tagType);
        BinaryTagBuffer binaryTagBuffer = binaryTagDriver.getBufferByType(tagType);
        int length = inputStream.readInt();
        for (int i = 0; i < length; i++) {
            listBinaryTag.add(binaryTagBuffer.read(binaryTagDriver, inputStream));
        }
        return listBinaryTag;
    }
}
