package eu.mshade.mwork.binarytag.wrap;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;

public interface BinaryTagWrap<T extends BinaryTag<?>> {

   default BinaryTag<?> wrap(BinaryTagBufferDriver binaryTagBufferDriver, T t){
       return t;
   }

}
