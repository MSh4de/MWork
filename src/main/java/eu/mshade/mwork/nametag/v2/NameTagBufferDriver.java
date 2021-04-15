package eu.mshade.mwork.nametag.v2;

import eu.mshade.mwork.nametag.v2.entity.CompoundBinaryTag;

import java.io.File;

public interface NameTagBufferDriver {

    NameTagBuffer getBufferByType(BinaryTagType binaryTagType);

    void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, File file);

    CompoundBinaryTag readCompoundBinaryTag(File file);

}
