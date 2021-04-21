package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.binarytag.buffer.*;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DefaultBinaryTagBufferDriver implements BinaryTagBufferDriver {

    private static final Map<BinaryTagType, BinaryTagBuffer> nameTagBufferMap = new HashMap<>();

    static {
        nameTagBufferMap.put(BinaryTagType.END, new EndBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.BYTE, new ByteBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.SHORT, new ShortBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.INTEGER, new IntegerBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.LONG, new LongBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.FLOAT, new FloatBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.DOUBLE, new DoubleBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.BYTE_ARRAY, new ByteArrayBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.STRING, new StringBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.LIST, new ListBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.COMPOUND, new CompoundBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.INTEGER_ARRAY, new IntegerArrayBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.LONG_ARRAY, new LongArrayBinaryTagBuffer());

        nameTagBufferMap.put(BinaryTagType.BOOLEAN, new BooleanBinaryTagBuffer());

        nameTagBufferMap.put(BinaryTagType.SHADE_BYTE_ARRAY, new ShadeByteArrayBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.SHADE_LIST, new ShadeListBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.SHADE_COMPOUND, new ShadeCompoundBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.SHADE_INTEGER_ARRAY, new ShadeIntegerArrayBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.SHADE_LONG_ARRAY, new ShadeLongArrayBinaryTagBuffer());

        nameTagBufferMap.put(BinaryTagType.ZSTD_BYTE_ARRAY, new ZstdByteArrayBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.ZSTD_LIST, new ZstdListBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.ZSTD_COMPOUND, new ZstdCompoundBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.ZSTD_INTEGER_ARRAY, new ZstdIntegerArrayBinaryTagBuffer());
        nameTagBufferMap.put(BinaryTagType.ZSTD_LONG_ARRAY, new ZstdLongArrayBinaryTagBuffer());
    }

    @Override
    public BinaryTagBuffer getBufferByType(BinaryTagType binaryTagType) {
        return nameTagBufferMap.get(binaryTagType);
    }

    @Override
    public void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            writeCompoundBinaryTag(compoundBinaryTag, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, OutputStream outputStream) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            byte[] bytes = "".getBytes(StandardCharsets.UTF_8);
            dataOutputStream.writeByte(BinaryTagType.COMPOUND.getType());
            dataOutputStream.writeShort(bytes.length);
            dataOutputStream.write(bytes);
            nameTagBufferMap.get(BinaryTagType.COMPOUND).write(this, dataOutputStream, compoundBinaryTag);
            dataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompoundBinaryTag readCompoundBinaryTag(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return readCompoundBinaryTag(fileInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CompoundBinaryTag();
    }

    @Override
    public CompoundBinaryTag readCompoundBinaryTag(InputStream inputStream) {
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            BinaryTagType type = BinaryTagType.getTagTypeById(dataInputStream.readByte());
            if (type != null) {
                short length = dataInputStream.readShort();
                byte[] bytes = new byte[length];
                dataInputStream.readFully(bytes);
                return (CompoundBinaryTag) nameTagBufferMap.get(BinaryTagType.COMPOUND).read(this, dataInputStream);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CompoundBinaryTag();
    }


}
