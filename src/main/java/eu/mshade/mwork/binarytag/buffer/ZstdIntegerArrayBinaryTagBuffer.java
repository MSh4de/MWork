package eu.mshade.mwork.binarytag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdIntegerArrayBinaryTagBuffer extends IntegerArrayBinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        super.writeZstd(binaryTagBufferDriver, outputStream, binaryTag);
    }

    @Override
    public IntegerArrayBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return (IntegerArrayBinaryTag) super.readZstd(binaryTagBufferDriver, inputStream);
    }
}
