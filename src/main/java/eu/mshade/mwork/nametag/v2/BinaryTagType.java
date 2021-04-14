package eu.mshade.mwork.nametag.v2;

import eu.mshade.mwork.nametag.v2.entity.*;

import java.util.HashMap;
import java.util.Map;


public enum BinaryTagType {

    END(EndBinaryTag.class, 0),
    BYTE(ByteBinaryTag.class, 1),
    SHORT(ShortBinaryTag.class, 2),
    INTEGER(IntegerBinaryTag.class, 3),
    LONG(LongBinaryTag.class, 4),
    FLOAT(FloatBinaryTag.class, 5),
    DOUBLE(DoubleBinaryTag.class, 6),
    BYTE_ARRAY(ByteArrayBinaryTag.class, 7),
    STRING(StringBinaryTag.class, 8),
    LIST(ListBinaryTag.class, 9),
    COMPOUND(CompoundBinaryTag.class, 10),
    INTEGER_ARRAY(IntegerArrayBinaryTag.class, 11),
    LONG_ARRAY(LongArrayBinaryTag.class, 12),

    ZSTD_BYTE_ARRAY(ZstdByteArrayBinaryTag.class, 13),
    ZSTD_LIST(ZstdByteArrayBinaryTag.class, 14),
    ZSTD_COMPOUND(ZstCompoundBinaryTag.class, 15),
    ZSTD_INTEGER_ARRAY(ZstdIntegerArrayBinaryTag.class, 16),
    ZSTD_LONG_ARRAY(ZstdLongArrayBinaryTag.class, 17);



    private static final Map<Integer, BinaryTagType> BY_ID = new HashMap<>();
    private static final Map<Class<? extends BinaryTag>, BinaryTagType> BY_CLASS = new HashMap<>();

    static {
        for (BinaryTagType binaryTagType : BinaryTagType.values()) {
            BY_ID.put(binaryTagType.getId(), binaryTagType);
            BY_CLASS.put(binaryTagType.getClazz(), binaryTagType);
        }
    }

    private final Class<? extends BinaryTag> aClass;
    private final int id;

    BinaryTagType(Class<? extends BinaryTag> aClass, int id) {
        this.aClass = aClass;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Class<? extends BinaryTag> getClazz() {
        return aClass;
    }

    public static BinaryTagType getTagTypeById(int id){
        return BY_ID.get(id);
    }

    public static BinaryTagType getTagTypeByClass(Class<? extends BinaryTag> aClass){
        return BY_CLASS.get(aClass);
    }


}
