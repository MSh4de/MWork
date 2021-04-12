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
    LONG_ARRAY(LongArrayBinaryTag.class, 12);

    private static final Map<Integer, BinaryTagType> BY_ID = new HashMap<>();

    static {
        for (BinaryTagType binaryTagType : BinaryTagType.values()) {
            BY_ID.put(binaryTagType.getId(), binaryTagType);
        }
    }

    private final Class<? extends BinaryTag<?>> aClass;
    private final int id;

    BinaryTagType(Class<? extends BinaryTag<?>> aClass, int id) {
        this.aClass = aClass;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static BinaryTagType getTagTypeById(int id){
        return BY_ID.get(id);
    }


}
