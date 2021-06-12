package eu.mshade.mwork.binarytag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdLongArrayBinaryTagBuffer extends LongArrayBinaryTagBuffer {
    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        super.writeZstd(outputStream, dataOutputStream -> super.write(binaryTagBufferDriver, dataOutputStream, binaryTag));
    }

    @Override
    public LongArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return (LongArrayBinaryTag) super.readZstd(inputStream, dataInputStream -> super.read(binaryTagBufferDriver, dataInputStream));
    }
}
