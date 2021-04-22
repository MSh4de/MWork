package eu.mshade.mwork.binarytag.wrap;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

public class ListBinaryTagWrap implements BinaryTagWrap<ListBinaryTag> {

    @Override
    public BinaryTag<?> wrap(BinaryTagBufferDriver binaryTagBufferDriver, ListBinaryTag binaryTags) {
        ListBinaryTag listBinaryTag = new ListBinaryTag(binaryTagBufferDriver.getBinaryTagTypeWrap(binaryTags.getElementType()));
        for (BinaryTag<?> binaryTag : listBinaryTag) {
            listBinaryTag.add(binaryTagBufferDriver.getBinaryTagWrap(listBinaryTag.getElementType()).wrap(binaryTagBufferDriver, binaryTag));
        }
        return listBinaryTag;
    }
}
