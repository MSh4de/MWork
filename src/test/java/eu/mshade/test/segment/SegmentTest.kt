package eu.mshade.test.segment

import eu.mshade.mwork.binarytag.BinaryTagDriver
import eu.mshade.mwork.binarytag.segment.SegmentBinaryTag
import java.io.File

fun main() {
    val binaryTagDriver = BinaryTagDriver()
    val segmentBinaryTag = SegmentBinaryTag(File("worlds/world/regions/0,0.msr"))

    for (segmentBlock in segmentBinaryTag.getSegmentBlocks()) {
        println("${String(segmentBlock.key)}, ${segmentBlock.segmentHeaders}")
    }
/*    val compound = CompoundBinaryTag()
    for (i in 0..1000) {
        compound.putInt("KEY$i", i)
    }

    segmentBinaryTag.writeCompound("myKey", binaryTagDriver, compound)*/

//    segmentBinaryTag.write("test", ByteArray(1000))

/*    val read = segmentBinaryTag.readCompound("myKey", binaryTagDriver)
    println(read.toPrettyString())*/
}