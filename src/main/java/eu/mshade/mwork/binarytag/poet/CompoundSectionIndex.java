package eu.mshade.mwork.binarytag.poet;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CompoundSectionIndex {


    private Map<String, List<SectionIndex>> sectionIndicesByName = new HashMap<>();
    private Queue<SectionIndex> freeSectionIndices = new ConcurrentLinkedQueue<>();
    private boolean hasChange;

    public void setSectionIndex(String key, List<SectionIndex> binaryTagIndices) {
        this.sectionIndicesByName.put(key, binaryTagIndices);
        this.hasChange = true;
    }

    public List<SectionIndex> getSectionIndices(String key) {
        return this.sectionIndicesByName.getOrDefault(key, new ArrayList<>());
    }

    public boolean containsKey(String key) {
        return this.sectionIndicesByName.containsKey(key);
    }

    public Map<String, List<SectionIndex>> getSectionIndicesByName() {
        return sectionIndicesByName;
    }

    public void addFreeSectionIndices(Collection<SectionIndex> sectionIndices){
        this.freeSectionIndices.addAll(sectionIndices);
        this.hasChange = true;
    }

    public void removeSectionIndices(String key) {
        this.sectionIndicesByName.remove(key);
    }

    public Queue<SectionIndex> getFreeSectionIndices() {
        return freeSectionIndices;
    }

    public void addFreeSectionIndices(SectionIndex sectionIndex) {
        this.freeSectionIndices.add(sectionIndex);
        this.hasChange = true;
    }

    public void removeFreeSectionIndices(SectionIndex sectionIndex) {
        this.freeSectionIndices.remove(sectionIndex);
        this.hasChange = true;
    }

    public void setFreeSectionIndices(Queue<SectionIndex> freeSectionIndices) {
        this.freeSectionIndices = freeSectionIndices;
        this.hasChange = true;
    }

    public boolean consume(){
        boolean copyHasChange = hasChange;
        this.hasChange = false;
        return copyHasChange;
    }

    @Override
    public String toString() {
        return "CompoundSectionIndex{" +
                "sectionIndicesByName=" + sectionIndicesByName +
                ", freeSectionIndices=" + freeSectionIndices +
                '}';
    }
}
