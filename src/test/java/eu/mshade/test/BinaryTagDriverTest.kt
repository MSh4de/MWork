package eu.mshade.test

import eu.mshade.mwork.binarytag.BinaryTagDriver
import eu.mshade.mwork.binarytag.ByteArrayBinaryTag
import eu.mshade.mwork.binarytag.ZstdCompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import org.junit.Test
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals

class BinaryTagDriverTest {

    private val binaryTagDriver = BinaryTagDriver()

    @Test
    fun `test compression zstd`(){
        val byteArray = ByteArray(1000)
        val zstdCompoundBinaryTag = ZstdCompoundBinaryTag()
        zstdCompoundBinaryTag.putByteArray("array", byteArray)

        val zstdOutputStream = ByteArrayOutputStream()
        binaryTagDriver.writeCompoundBinaryTag(zstdCompoundBinaryTag, zstdOutputStream)

        val compoundBinaryTag = CompoundBinaryTag()
        compoundBinaryTag.putByteArray("array", byteArray)

        val outputStream = ByteArrayOutputStream()
        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, outputStream)

        assertEquals(true, zstdOutputStream.size() < outputStream.size(), "zstd should be smaller than normal")
    }



}