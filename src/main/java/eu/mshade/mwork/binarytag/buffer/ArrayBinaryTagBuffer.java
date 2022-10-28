package eu.mshade.mwork.binarytag.buffer;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBuffer;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.ArrayBinaryTag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ArrayBinaryTagBuffer implements BinaryTagBuffer {

    @Override
    public void write(BinaryTagDriver binaryTagDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws IOException {
        ArrayBinaryTag<?> arrayBinaryTag = (ArrayBinaryTag<?>) binaryTag;
        BinaryTag<?>[] arrayBinaryTagValue = arrayBinaryTag.getValue();
        BinaryTagType elementType = arrayBinaryTag.getElementType();
        outputStream.writeByte(elementType.getType());
        outputStream.writeInt(arrayBinaryTagValue.length);
        BinaryTagBuffer bufferByType = binaryTagDriver.getBufferByType(elementType);
        for (BinaryTag<?> tag : arrayBinaryTagValue) {

            bufferByType.write(binaryTagDriver, outputStream, tag);
        }
    }

    @Override
    public BinaryTag<?> read(BinaryTagDriver binaryTagDriver, DataInputStream inputStream) throws IOException {
        BinaryTagType tagTypeById = BinaryTagType.getTagTypeById(inputStream.readByte());
        int size = inputStream.readInt();
        BinaryTag<?>[] binaryTags = new BinaryTag[size];
        BinaryTagBuffer binaryTagBuffer = binaryTagDriver.getBufferByType(tagTypeById);
        for (int i = 0; i < size; i++) {
            binaryTags[i] = binaryTagBuffer.read(binaryTagDriver, inputStream);
        }
        return new ArrayBinaryTag<>(tagTypeById, binaryTags);
    }
}
