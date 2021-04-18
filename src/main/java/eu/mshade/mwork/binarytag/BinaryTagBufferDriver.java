package eu.mshade.mwork.nametag;

import eu.mshade.mwork.nametag.entity.CompoundBinaryTag;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface BinaryTagBufferDriver {

    BinaryTagBuffer getBufferByType(BinaryTagType binaryTagType);

    void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, File file);

    void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, OutputStream outputStream);

    CompoundBinaryTag readCompoundBinaryTag(File file);

    CompoundBinaryTag readCompoundBinaryTag(InputStream inputStream);

}
