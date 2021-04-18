package eu.mshade.mwork.nametag;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface BinaryTagBuffer {

    void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception;

    BinaryTag<?> read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception;

}
