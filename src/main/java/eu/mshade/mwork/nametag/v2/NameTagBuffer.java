package eu.mshade.mwork.nametag.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface NameTagBuffer<T extends BinaryTag<?>> {

    void write(NameTagBufferDriver nameTagBufferDriver, DataOutputStream outputStream, T t) throws Exception;

    T read(NameTagBufferDriver nameTagBufferDriver, DataInputStream inputStream) throws Exception;

}
