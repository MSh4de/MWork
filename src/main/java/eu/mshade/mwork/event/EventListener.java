package eu.mshade.mwork.event;

@FunctionalInterface
public interface EventListener<E> {

    void onEvent(E event, EventContainer eventContainer);

}
