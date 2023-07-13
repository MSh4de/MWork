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
        val zstdCompoundBinaryTag = ZstdCompoundBinaryTag()
        zstdCompoundBinaryTag.putBinaryTag("array", ByteArrayBinaryTag(ByteArray(1000)))

        val zstdOutputStream = ByteArrayOutputStream()
        binaryTagDriver.writeCompoundBinaryTag(zstdCompoundBinaryTag, zstdOutputStream)

        val compoundBinaryTag = CompoundBinaryTag()
        compoundBinaryTag.putBinaryTag("array", ByteArrayBinaryTag(ByteArray(1000)))

        val outputStream = ByteArrayOutputStream()
        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, outputStream)


        println("zstd: ${zstdOutputStream.size()}")
        println("normal: ${outputStream.size()}")

        assertEquals(true, zstdOutputStream.size() < outputStream.size())
    }



}