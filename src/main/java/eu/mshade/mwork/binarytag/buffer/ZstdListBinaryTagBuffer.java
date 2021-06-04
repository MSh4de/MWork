package eu.mshade.mwork.binarytag.buffer;

import com.github.luben.zstd.Zstd;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.ZstdBinaryTag;
import eu.mshade.mwork.binarytag.entity.IntegerArrayBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.LongArrayBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ZstdListBinaryTagBuffer extends ListBinaryTagBuffer {

    @Override
    public void write(BinaryTagBufferDriver binaryTagBufferDriver, DataOutputStream outputStream, BinaryTag<?> binaryTag) throws Exception {
        super.writeZstd(binaryTagBufferDriver, outputStream, binaryTag);
    }

    @Override
    public ListBinaryTag read(BinaryTagBufferDriver binaryTagBufferDriver, DataInputStream inputStream) throws Exception {
        return (ListBinaryTag) super.readZstd(binaryTagBufferDriver, inputStream);
    }
}
