package eu.mshade.mwork.binarytag.wrap;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class CompoundBinaryTagWrap implements BinaryTagWrap<CompoundBinaryTag> {

    @Override
    public BinaryTag<?> wrap(BinaryTagBufferDriver binaryTagBufferDriver, CompoundBinaryTag compoundBinaryTag) {
        CompoundBinaryTag binaryTag = new CompoundBinaryTag();
        compoundBinaryTag.getValue().forEach((s, tag) -> {
            binaryTag.putBinaryTag(s, binaryTagBufferDriver.getBinaryTagWrap(binaryTagBufferDriver.getBinaryTagTypeWrap(tag.getType())).wrap(binaryTagBufferDriver, tag));
        });
        return binaryTag;
    }
}
