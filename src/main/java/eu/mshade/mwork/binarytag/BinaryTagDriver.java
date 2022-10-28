package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.binarytag.buffer.*;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BinaryTagDriver {

    protected final Map<BinaryTagType, BinaryTagBuffer> binaryTagBufferByBinaryTagType = new HashMap<>();
    protected final Map<Class<?>, BinaryTagMarshal<Object>> binaryTagMarshalByType = new HashMap<>();
    protected final Map<Class<?>, BinaryTagDynamicMarshal> binaryTagDynamicMarshalByType = new HashMap<>();


    public BinaryTagDriver() {
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.END, new EndBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.BYTE, new ByteBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.SHORT, new ShortBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.INTEGER, new IntegerBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.LONG, new LongBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.FLOAT, new FloatBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.DOUBLE, new DoubleBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.STRING, new StringBinaryTagBuffer());

       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.BYTE_ARRAY, new ByteArrayBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.LIST, new ListBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.COMPOUND, new CompoundBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.INTEGER_ARRAY, new IntegerArrayBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.LONG_ARRAY, new LongArrayBinaryTagBuffer());

       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.BOOLEAN, new BooleanBinaryTagBuffer());

       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.SHADE_BYTE_ARRAY, new ShadeByteArrayBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.SHADE_LIST, new ShadeListBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.SHADE_COMPOUND, new ShadeCompoundBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.SHADE_INTEGER_ARRAY, new ShadeIntegerArrayBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.SHADE_LONG_ARRAY, new ShadeLongArrayBinaryTagBuffer());

       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.ZSTD_BYTE_ARRAY, new ZstdByteArrayBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.ZSTD_LIST, new ZstdListBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.ZSTD_COMPOUND, new ZstdCompoundBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.ZSTD_INTEGER_ARRAY, new ZstdIntegerArrayBinaryTagBuffer());
       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.ZSTD_LONG_ARRAY, new ZstdLongArrayBinaryTagBuffer());

       this.binaryTagBufferByBinaryTagType.put(BinaryTagType.ARRAY, new ArrayBinaryTagBuffer());
    }


    public BinaryTagBuffer getBufferByType(BinaryTagType binaryTagType) {
        return binaryTagBufferByBinaryTagType.get(binaryTagType);
    }

    public void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, File file) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writeCompoundBinaryTag(compoundBinaryTag, fileOutputStream);
    }

    public void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, OutputStream outputStream) {
        try(DataOutputStream dataOutputStream = new DataOutputStream(outputStream)){
            byte[] bytes = "".getBytes(StandardCharsets.UTF_8);
            dataOutputStream.writeByte(compoundBinaryTag.getType().getType());
            dataOutputStream.writeShort(bytes.length);
            dataOutputStream.write(bytes);
            binaryTagBufferByBinaryTagType.get(compoundBinaryTag.getType()).write(this, dataOutputStream, compoundBinaryTag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompoundBinaryTag readCompoundBinaryTagOrDefault(File file, Supplier<CompoundBinaryTag> supplier){
        try {
            return readCompoundBinaryTag(file);
        } catch (Exception e) {
            return supplier.get();
        }
    }

    public CompoundBinaryTag readCompoundBinaryTagOrDefault(InputStream inputStream, Supplier<CompoundBinaryTag> supplier){
        try {
            return readCompoundBinaryTag(inputStream);
        } catch (Exception e) {
            return supplier.get();
        }
    }


    public CompoundBinaryTag readCompoundBinaryTag(File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        }catch (FileNotFoundException e){
           throw new RuntimeException(e);
        }

        return readCompoundBinaryTag(fileInputStream);
    }

    public CompoundBinaryTag readCompoundBinaryTag(InputStream inputStream) {
        try(DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            BinaryTagType type = BinaryTagType.getTagTypeById(dataInputStream.readByte());
            if (type != null && type != BinaryTagType.END) {
                short length = dataInputStream.readShort();
                byte[] bytes = new byte[length];
                dataInputStream.readFully(bytes);
                return (CompoundBinaryTag) binaryTagBufferByBinaryTagType.get(type).read(this, dataInputStream);
            }
            return new CompoundBinaryTag();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void registerMarshal(BinaryTagMarshal<?> binaryTagMarshal, Class<?>... types){
        for (Class<?> type : types) {
            this.binaryTagMarshalByType.put(type, (BinaryTagMarshal<Object>) binaryTagMarshal);
        }
    }

    public <T> void registerMarshal(Class<T> type, BinaryTagMarshal<T> binaryTagMarshal){
        this.binaryTagMarshalByType.put(type, (BinaryTagMarshal<Object>) binaryTagMarshal);
    }
    public BinaryTag<?> marshal(Object o, Class<?> type){
        try {
            return this.binaryTagMarshalByType.get(type).serialize(this, o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BinaryTag<?> marshal(Object o){
        return this.marshal(o, o.getClass());
    }

    public <T> T unMarshal(BinaryTag<?> binaryTag, Class<T> type){
        try {
            return type.cast(this.binaryTagMarshalByType.get(type).deserialize(this, binaryTag));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registerDynamicMarshal(BinaryTagDynamicMarshal binaryTagDynamicMarshal){
        this.binaryTagDynamicMarshalByType.put(binaryTagDynamicMarshal.getClass(), binaryTagDynamicMarshal);
    }

    public <T extends BinaryTagDynamicMarshal> T getDynamicMarshal(Class<T> type){
        return type.cast(this.binaryTagDynamicMarshalByType.get(type));
    }
}
