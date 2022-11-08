package eu.mshade.mwork.binarytag.segment

import java.util.concurrent.ConcurrentLinkedQueue

class SegmentSectionBucket {
    private val sectionIndicesByName: MutableMap<String, MutableList<SegmentSection>> = mutableMapOf()
    private var freeSegmentSection = ConcurrentLinkedQueue<SegmentSection>()
    private var hasChange = false

    fun setSectionIndex(key: String, binaryTagIndices: MutableList<SegmentSection>) {
        sectionIndicesByName[key] = binaryTagIndices
        hasChange = true
    }

    fun getSectionIndices(key: String): MutableList<SegmentSection>? {
        return sectionIndicesByName[key]
    }

    fun containsKey(key: String): Boolean {
        return sectionIndicesByName.containsKey(key)
    }

    fun getSectionIndicesByName(): Map<String, List<SegmentSection>> {
        return sectionIndicesByName
    }

    fun addFreeSectionIndices(sectionIndices: Collection<SegmentSection>) {
        freeSegmentSection.addAll(sectionIndices)
        hasChange = true
    }

    fun removeSectionIndices(key: String) {
        sectionIndicesByName.remove(key)
    }

    fun getFreeCarbonSection(): ConcurrentLinkedQueue<SegmentSection> {
        return freeSegmentSection
    }

    fun poolFreeCarbonSection(): SegmentSection? {
        return freeSegmentSection.poll()
    }


    fun addFreeSectionIndices(segmentSection: SegmentSection) {
        freeSegmentSection.add(segmentSection)
        hasChange = true
    }

    fun removeFreeSectionIndices(segmentSection: SegmentSection) {
        freeSegmentSection.remove(segmentSection)
        hasChange = true
    }

    fun setFreeSectionIndices(freeSectionIndices: ConcurrentLinkedQueue<SegmentSection>) {
        this.freeSegmentSection = freeSectionIndices
        hasChange = true
    }

    fun consume(): Boolean {
        val copyHasChange = hasChange
        hasChange = false
        return copyHasChange
    }

    override fun toString(): String {
        return "CarbonSectionBucket(sectionIndicesByName=$sectionIndicesByName, freeCarbonSection=$freeSegmentSection)"
    }


}