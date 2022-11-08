package eu.mshade.mwork.binarytag.segment

class SegmentSection(var index: Int, var start: Int, val end: Int) {

    val size: Int
        get() = end - start

    override fun toString(): String {
        return "CarbonSection(index=$index, start=$start, end=$end)"
    }


}