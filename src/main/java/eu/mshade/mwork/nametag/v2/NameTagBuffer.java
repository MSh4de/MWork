package eu.mshade.mwork.nametag.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface NameTagBuffer {

    void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception;

    BinaryTag<?> read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception;

}
