package eu.mshade.mwork.binarytag.segment

import java.util.Objects

data class SegmentBlock(val key: ByteArray, val segmentHeaders: MutableList<SegmentHeader> = mutableListOf()) {

    fun getSpace(): Int {
        return segmentHeaders.sumOf { it.getPayloadSize() }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SegmentBlock) return false

        if (!key.contentEquals(other.key)) return false
        return segmentHeaders == other.segmentHeaders
    }

    override fun hashCode(): Int {
        return Objects.hash(key, segmentHeaders)
    }
}