package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.binarytag.buffer.*;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DefaultBinaryTagBufferDriver implements BinaryTagBufferDriver {

    private static final Map<BinaryTagType, BinaryTagBuffer> binaryTagBufferMap = new HashMap<>();



    static {
        binaryTagBufferMap.put(BinaryTagType.END, new EndBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.BYTE, new ByteBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.SHORT, new ShortBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.INTEGER, new IntegerBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.LONG, new LongBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.FLOAT, new FloatBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.DOUBLE, new DoubleBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.BYTE_ARRAY, new ByteArrayBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.STRING, new StringBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.LIST, new ListBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.COMPOUND, new CompoundBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.INTEGER_ARRAY, new IntegerArrayBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.LONG_ARRAY, new LongArrayBinaryTagBuffer());

        binaryTagBufferMap.put(BinaryTagType.BOOLEAN, new BooleanBinaryTagBuffer());

        binaryTagBufferMap.put(BinaryTagType.SHADE_BYTE_ARRAY, new ShadeByteArrayBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.SHADE_LIST, new ShadeListBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.SHADE_COMPOUND, new ShadeCompoundBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.SHADE_INTEGER_ARRAY, new ShadeIntegerArrayBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.SHADE_LONG_ARRAY, new ShadeLongArrayBinaryTagBuffer());

        binaryTagBufferMap.put(BinaryTagType.ZSTD_BYTE_ARRAY, new ZstdByteArrayBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.ZSTD_LIST, new ZstdListBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.ZSTD_COMPOUND, new ZstdCompoundBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.ZSTD_INTEGER_ARRAY, new ZstdIntegerArrayBinaryTagBuffer());
        binaryTagBufferMap.put(BinaryTagType.ZSTD_LONG_ARRAY, new ZstdLongArrayBinaryTagBuffer());
    }

    @Override
    public BinaryTagBuffer getBufferByType(BinaryTagType binaryTagType) {
        return binaryTagBufferMap.get(binaryTagType);
    }

    @Override
    public void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            writeCompoundBinaryTag(compoundBinaryTag, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, OutputStream outputStream) {
        try(DataOutputStream dataOutputStream = new DataOutputStream(outputStream);){
            byte[] bytes = "".getBytes(StandardCharsets.UTF_8);
            dataOutputStream.writeByte(BinaryTagType.COMPOUND.getType());
            dataOutputStream.writeShort(bytes.length);
            dataOutputStream.write(bytes);
            binaryTagBufferMap.get(BinaryTagType.COMPOUND).write(this, dataOutputStream, compoundBinaryTag);
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
        try(DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            BinaryTagType type = BinaryTagType.getTagTypeById(dataInputStream.readByte());
            if (type != null) {
                short length = dataInputStream.readShort();
                byte[] bytes = new byte[length];
                dataInputStream.readFully(bytes);
                return (CompoundBinaryTag) binaryTagBufferMap.get(BinaryTagType.COMPOUND).read(this, dataInputStream);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CompoundBinaryTag();
    }
}
