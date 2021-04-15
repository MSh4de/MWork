package eu.mshade.mwork.nametag.v2;

import eu.mshade.mwork.nametag.v2.buffer.*;
import eu.mshade.mwork.nametag.v2.entity.CompoundBinaryTag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DefaultNameTagBufferDriver implements NameTagBufferDriver {

    private static final Map<BinaryTagType, NameTagBuffer> nameTagBufferMap = new HashMap<>();

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

        nameTagBufferMap.put(BinaryTagType.ZSTD_COMPOUND, new ZstdCompoundBinaryTagBuffer());
    }

    @Override
    public NameTagBuffer getBufferByType(BinaryTagType binaryTagType) {
        return nameTagBufferMap.get(binaryTagType);
    }

    @Override
    public void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream outputStream = new DataOutputStream(fileOutputStream);

            byte[] bytes = "".getBytes(StandardCharsets.UTF_8);
            outputStream.writeByte(BinaryTagType.COMPOUND.getId());
            outputStream.writeShort(bytes.length);
            outputStream.write(bytes);

            nameTagBufferMap.get(BinaryTagType.COMPOUND).write(this, outputStream, compoundBinaryTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompoundBinaryTag readCompoundBinaryTag(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream inputStream = new DataInputStream(fileInputStream);
            BinaryTagType type = BinaryTagType.getTagTypeById(inputStream.readByte());
            if (type != null) {
                short length = inputStream.readShort();
                byte[] bytes = new byte[length];
                inputStream.readFully(bytes);
                return (CompoundBinaryTag) nameTagBufferMap.get(BinaryTagType.COMPOUND).read(this, inputStream);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CompoundBinaryTag();
    }


}
