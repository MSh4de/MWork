package eu.mshade.mwork.nametag.v2.entity;

import eu.mshade.mwork.nametag.v2.BinaryTag;
import eu.mshade.mwork.nametag.v2.BinaryTagType;

import java.util.LinkedHashMap;
import java.util.Map;

public class CompoundBinaryTag implements BinaryTag<Map<String, BinaryTag<?>>> {

    private final Map<String, BinaryTag<?>> binaryTagMap = new LinkedHashMap<>();

    @Override
    public BinaryTagType getType() {
        return BinaryTagType.COMPOUND;
    }

    public void putBinaryTag(String key, BinaryTag<?> value){
        binaryTagMap.put(key, value);
    }

    public BinaryTag<?> getBinaryTag(String key){
        return binaryTagMap.get(key);
    }

    public void putByte(String key, byte value){
        this.putBinaryTag(key, new ByteBinaryTag(value));
    }

    public byte getByte(String key){
        return (byte) this.getBinaryTag(key).getValue();
    }

    public void putShort(String key, short value){
        this.putBinaryTag(key, new ShortBinaryTag(value));
    }

    public short getShort(String key){
        return (short) this.getBinaryTag(key).getValue();
    }

    public void putInt(String key, int value){ this.putBinaryTag(key, new IntegerBinaryTag(value)); }

    public void putLong(String key, long value){
        this.putBinaryTag(key, new LongBinaryTag(value));
    }

    public void putDouble(String key, double value){
        this.putBinaryTag(key, new DoubleBinaryTag(value));
    }

    public void putByteArray(String key, byte[] value){
        this.putBinaryTag(key, new ByteArrayBinaryTag(value));
    }

    public void putString(String key, String value){
        this.putBinaryTag(key, new StringBinaryTag(value));
    }

    public void putIntArray(String key, int[] value){
        this.putBinaryTag(key, new IntegerArrayBinaryTag(value));
    }

    public void putLongArray(String key, long[] value){
        this.putBinaryTag(key, new LongArrayBinaryTag(value));
    }

    @Override
    public Map<String, BinaryTag<?>> getValue() {
        return binaryTagMap;
    }
}
