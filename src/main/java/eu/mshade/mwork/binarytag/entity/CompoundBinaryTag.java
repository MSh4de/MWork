package eu.mshade.mwork.binarytag.entity;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.ShadeBinaryTag;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CompoundBinaryTag implements BinaryTag<Map<String, BinaryTag<?>>>, ZstdBinaryTag<ZstdCompoundBinaryTag>, ShadeBinaryTag<ShadeCompoundBinaryTag> {

    private final Map<String, BinaryTag<?>> binaryTagMap;

    public CompoundBinaryTag(Map<String, BinaryTag<?>> binaryTagMap) {
        this.binaryTagMap = binaryTagMap;
    }

    public CompoundBinaryTag() {
        this(new LinkedHashMap<>());
    }

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

    public void addCompound(CompoundBinaryTag compoundBinaryTag) {
        binaryTagMap.putAll(compoundBinaryTag.getValue());
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

    public int getInt(String key){
        return (int) this.getBinaryTag(key).getValue();
    }

    public void putLong(String key, long value){
        this.putBinaryTag(key, new LongBinaryTag(value));
    }

    public long getLong(String key){
        return (long) this.getBinaryTag(key).getValue();
    }

    public void putDouble(String key, double value){
        this.putBinaryTag(key, new DoubleBinaryTag(value));
    }

    public double getDouble(String key){
        return (double) this.getBinaryTag(key).getValue();
    }

    public void putByteArray(String key, byte[] value){
        this.putBinaryTag(key, new ByteArrayBinaryTag(value));
    }

    public byte[] getByteArray(String key){
        return (byte[]) this.getBinaryTag(key).getValue();
    }

    public void putString(String key, String value){
        this.putBinaryTag(key, new StringBinaryTag(value));
    }

    public String getString(String key){
        return (String) this.getBinaryTag(key).getValue();
    }

    public void putIntArray(String key, int[] value){
        this.putBinaryTag(key, new IntegerArrayBinaryTag(value));
    }

    public int[] getIntArray(String key){
        return (int[]) this.getBinaryTag(key).getValue();
    }

    public void putLongArray(String key, long[] value){
        this.putBinaryTag(key, new LongArrayBinaryTag(value));
    }

    public long[] getLongArray(String key){
        return (long[]) this.getBinaryTag(key).getValue();
    }

    public void putBoolean(String key, boolean value) {
        this.putBinaryTag(key, new BooleanBinaryTag(value));
    }

    public boolean getBoolean(String key){
        return (boolean) this.getBinaryTag(key).getValue();
    }

    public void putFloat(String key, float value) { this.putBinaryTag(key, new FloatBinaryTag(value)); }

    public float getFloat(String key) { return (float) this.getBinaryTag(key).getValue(); }


    @Override
    public Map<String, BinaryTag<?>> getValue() {
        return binaryTagMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompoundBinaryTag tag = (CompoundBinaryTag) o;
        return Objects.equals(binaryTagMap, tag.binaryTagMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binaryTagMap);
    }

    @Override
    public String toString() {
        return "CompoundBinaryTag{" +
                "binaryTagMap=" + binaryTagMap +
                '}';
    }

    @Override
    public ZstdCompoundBinaryTag toZstd() {
        return new ZstdCompoundBinaryTag(binaryTagMap);
    }


    @Override
    public ShadeCompoundBinaryTag toShade() {
        return new ShadeCompoundBinaryTag(binaryTagMap);
    }
}
