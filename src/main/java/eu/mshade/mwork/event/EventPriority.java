package eu.mshade.mwork.event;

public enum EventPriority {

    HIGH(3),
    NORMAL(2),
    LOW(1);

    private int weight;

    EventPriority(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
