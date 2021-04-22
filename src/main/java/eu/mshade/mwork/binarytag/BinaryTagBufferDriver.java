package eu.mshade.mwork.binarytag;

import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.wrap.BinaryTagWrap;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface BinaryTagBufferDriver {

    BinaryTagBuffer getBufferByType(BinaryTagType binaryTagType);

    void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, File file);

    void writeCompoundBinaryTag(CompoundBinaryTag compoundBinaryTag, OutputStream outputStream);

    CompoundBinaryTag readCompoundBinaryTag(File file);

    CompoundBinaryTag readCompoundBinaryTag(InputStream inputStream);

    CompoundBinaryTag wrapToMinecraft(CompoundBinaryTag compoundBinaryTag);

    BinaryTagWrap<BinaryTag<?>> getBinaryTagWrap(BinaryTagType binaryTagType);

    BinaryTagType getBinaryTagTypeWrap(BinaryTagType binaryTagType);

}
