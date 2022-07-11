package eu.mshade.mwork.binarytag.poet;

public class SectionIndex {

    private int index, start, end;

    public SectionIndex(int index, int start, int end) {
        this.index = index;
        this.start = start;
        this.end = end;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public int getSize(){
        return end - start;
    }

    @Override
    public String toString() {
        return "SectionIndex{" +
                "index=" + index +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
