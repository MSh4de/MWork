package eu.mshade.mwork.binarytag.carbon

class CarbonSectionBucket {
    private val sectionIndicesByName: MutableMap<String, MutableList<CarbonSection>> = mutableMapOf()
    private var freeCarbonSection: MutableList<CarbonSection> = mutableListOf()
    private var hasChange = false

    fun setSectionIndex(key: String, binaryTagIndices: MutableList<CarbonSection>) {
        sectionIndicesByName[key] = binaryTagIndices
        hasChange = true
    }

    fun getSectionIndices(key: String): MutableList<CarbonSection>? {
        return sectionIndicesByName[key]
    }

    fun containsKey(key: String): Boolean {
        return sectionIndicesByName.containsKey(key)
    }

    fun getSectionIndicesByName(): Map<String, List<CarbonSection>> {
        return sectionIndicesByName
    }

    fun addFreeSectionIndices(sectionIndices: Collection<CarbonSection>) {
        freeCarbonSection.addAll(sectionIndices)
        hasChange = true
    }

    fun removeSectionIndices(key: String) {
        sectionIndicesByName.remove(key)
    }

    fun getFreeCarbonSection(): MutableList<CarbonSection> {
        return freeCarbonSection
    }

    fun poolFreeCarbonSection(): CarbonSection? {
        if (freeCarbonSection.isEmpty()) return null
        return freeCarbonSection.removeAt(0)
    }


    fun addFreeSectionIndices(carbonSection: CarbonSection) {
        freeCarbonSection.add(carbonSection)
        hasChange = true
    }

    fun removeFreeSectionIndices(carbonSection: CarbonSection) {
        freeCarbonSection.remove(carbonSection)
        hasChange = true
    }

    fun setFreeSectionIndices(freeSectionIndices: MutableList<CarbonSection>) {
        this.freeCarbonSection = freeSectionIndices
        hasChange = true
    }

    fun consume(): Boolean {
        val copyHasChange = hasChange
        hasChange = false
        return copyHasChange
    }

    override fun toString(): String {
        return "CarbonSectionBucket(sectionIndicesByName=$sectionIndicesByName, freeCarbonSection=$freeCarbonSection)"
    }


}