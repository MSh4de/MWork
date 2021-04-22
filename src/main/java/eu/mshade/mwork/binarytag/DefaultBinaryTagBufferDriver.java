package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.binarytag.buffer.*;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.wrap.BinaryTagWrap;
import eu.mshade.mwork.binarytag.wrap.CompoundBinaryTagWrap;
import eu.mshade.mwork.binarytag.wrap.ListBinaryTagWrap;
import eu.mshade.mwork.binarytag.wrap.array.ByteArrayBinaryTagWrap;
import eu.mshade.mwork.binarytag.wrap.array.IntegerArrayBinaryTagWrap;
import eu.mshade.mwork.binarytag.wrap.array.LongArrayBinaryTagWrap;
import eu.mshade.mwork.binarytag.wrap.primitive.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DefaultBinaryTagBufferDriver implements BinaryTagBufferDriver {

    private static final Map<BinaryTagType, BinaryTagBuffer> binaryTagBufferMap = new HashMap<>();
    private static final Map<BinaryTagType, BinaryTagWrap> binaryTagWrapMap = new HashMap<>();
    private static final Map<BinaryTagType, BinaryTagType> binaryTagTypeWrapMap = new HashMap<>();

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

        binaryTagTypeWrapMap.put(BinaryTagType.BYTE, BinaryTagType.BYTE);
        binaryTagTypeWrapMap.put(BinaryTagType.SHORT, BinaryTagType.SHORT);
        binaryTagTypeWrapMap.put(BinaryTagType.INTEGER, BinaryTagType.INTEGER);
        binaryTagTypeWrapMap.put(BinaryTagType.LONG, BinaryTagType.LONG);
        binaryTagTypeWrapMap.put(BinaryTagType.FLOAT, BinaryTagType.FLOAT);
        binaryTagTypeWrapMap.put(BinaryTagType.DOUBLE, BinaryTagType.DOUBLE);
        binaryTagTypeWrapMap.put(BinaryTagType.BYTE_ARRAY, BinaryTagType.BYTE_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.STRING, BinaryTagType.STRING);
        binaryTagTypeWrapMap.put(BinaryTagType.LIST, BinaryTagType.LIST);
        binaryTagTypeWrapMap.put(BinaryTagType.COMPOUND, BinaryTagType.COMPOUND);
        binaryTagTypeWrapMap.put(BinaryTagType.INTEGER_ARRAY, BinaryTagType.INTEGER_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.LONG_ARRAY, BinaryTagType.LONG_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.SHADE_BYTE_ARRAY, BinaryTagType.BYTE_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.SHADE_LIST, BinaryTagType.LIST);
        binaryTagTypeWrapMap.put(BinaryTagType.SHADE_COMPOUND, BinaryTagType.COMPOUND);
        binaryTagTypeWrapMap.put(BinaryTagType.SHADE_INTEGER_ARRAY, BinaryTagType.INTEGER_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.SHADE_LONG_ARRAY, BinaryTagType.LONG_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.ZSTD_BYTE_ARRAY, BinaryTagType.BYTE_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.ZSTD_LIST, BinaryTagType.LIST);
        binaryTagTypeWrapMap.put(BinaryTagType.ZSTD_COMPOUND, BinaryTagType.COMPOUND);
        binaryTagTypeWrapMap.put(BinaryTagType.ZSTD_INTEGER_ARRAY, BinaryTagType.INTEGER_ARRAY);
        binaryTagTypeWrapMap.put(BinaryTagType.ZSTD_LONG_ARRAY, BinaryTagType.LONG_ARRAY);


        binaryTagWrapMap.put(BinaryTagType.BYTE, new ByteBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.SHORT, new ShortBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.INTEGER, new IntegerBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.LONG, new LongBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.FLOAT, new FloatBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.DOUBLE, new DoubleBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.BYTE_ARRAY, new ByteArrayBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.STRING, new StringBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.LIST, new ListBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.COMPOUND, new CompoundBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.INTEGER_ARRAY, new IntegerArrayBinaryTagWrap());
        binaryTagWrapMap.put(BinaryTagType.LONG_ARRAY, new LongArrayBinaryTagWrap());

        //binaryTagWrapMap.put(BinaryTagType.BOOLEAN, new BooleanBinaryTagBuffer());

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
            binaryTagBufferMap.get(BinaryTagType.COMPOUND).write(this, dataOutputStream, compoundBinaryTag);
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
                return (CompoundBinaryTag) binaryTagBufferMap.get(BinaryTagType.COMPOUND).read(this, dataInputStream);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CompoundBinaryTag();
    }

    @Override
    public CompoundBinaryTag wrapToMinecraft(CompoundBinaryTag compoundBinaryTag) {
        return (CompoundBinaryTag) binaryTagWrapMap.get(BinaryTagType.COMPOUND).wrap(this, compoundBinaryTag);
    }

    @Override
    public BinaryTagWrap<BinaryTag<?>> getBinaryTagWrap(BinaryTagType binaryTagType) {
        return (BinaryTagWrap<BinaryTag<?>>) binaryTagWrapMap.get(binaryTagType);
    }

    @Override
    public BinaryTagType getBinaryTagTypeWrap(BinaryTagType binaryTagType) {
        return binaryTagTypeWrapMap.get(binaryTagType);
    }


}
