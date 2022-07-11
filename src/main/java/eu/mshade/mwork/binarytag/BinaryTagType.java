package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.binarytag.entity.*;

import java.util.HashMap;
import java.util.Map;


public enum BinaryTagType {

    END(EndBinaryTag.class, 0),
    BYTE(ByteBinaryTag.class,1),
    SHORT(ShortBinaryTag.class, 2),
    INTEGER(IntegerBinaryTag.class,3),
    LONG(LongBinaryTag.class, 4),
    FLOAT(FloatBinaryTag.class, 5),
    DOUBLE(DoubleBinaryTag.class, 6),
    BYTE_ARRAY(ByteArrayBinaryTag.class, 7),
    STRING(StringBinaryTag.class, 8),
    LIST(ListBinaryTag.class, 9),
    COMPOUND(CompoundBinaryTag.class, 10),
    INTEGER_ARRAY(IntegerArrayBinaryTag.class,11),
    LONG_ARRAY(LongArrayBinaryTag.class,12),

    BOOLEAN(BooleanBinaryTag.class, 13),

    SHADE_BYTE_ARRAY(ShadeByteArrayBinaryTag.class, 14),
    SHADE_LIST(ShadeListBinaryTag.class, 15),
    SHADE_COMPOUND(ShadeCompoundBinaryTag.class, 16),
    SHADE_INTEGER_ARRAY(ShadeIntegerArrayBinaryTag.class, 17),
    SHADE_LONG_ARRAY(ShadeLongArrayBinaryTag.class, 18),

    ZSTD_BYTE_ARRAY(ZstdByteArrayBinaryTag.class, 19),
    ZSTD_LIST(ZstdListBinaryTag.class, 20),
    ZSTD_COMPOUND(ZstdCompoundBinaryTag.class, 21),
    ZSTD_INTEGER_ARRAY(ZstdIntegerArrayBinaryTag.class, 22),
    ZSTD_LONG_ARRAY(ZstdLongArrayBinaryTag.class, 23);



    private static final Map<Integer, BinaryTagType> BY_ID = new HashMap<>();
    private static final Map<Class<? extends BinaryTag<?>>, BinaryTagType> BY_CLASS = new HashMap<>();

    static {
        for (BinaryTagType binaryTagType : BinaryTagType.values()) {
            BY_ID.put(binaryTagType.getType(), binaryTagType);
            BY_CLASS.put(binaryTagType.getClazz(), binaryTagType);
        }
    }

    private final Class<? extends BinaryTag<?>> aClass;
    private final int id;

    BinaryTagType(Class<? extends BinaryTag<?>> aClass, int id) {
        this.aClass = aClass;
        this.id = id;
    }

    public int getType() {
        return id;
    }

    public Class<? extends BinaryTag<?>> getClazz() {
        return aClass;
    }

    public static BinaryTagType getTagTypeById(int id){
        return BY_ID.get(id);
    }

    public static BinaryTagType getTagTypeByClass(Class<? extends BinaryTag<?>> aClass){
        return BY_CLASS.get(aClass);
    }
}
