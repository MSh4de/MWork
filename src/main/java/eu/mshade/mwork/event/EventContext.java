package eu.mshade.mwork.event;

public interface EventContext<E> {

    EventContext<E> withEventFilter(EventFilter eventFilter);

    EventContext<E> withEventPriority(EventPriority eventPriority);

    EventPriority getEventPriority();

    EventFilter getEventFilter();

    EventListener<E> getEventListener();

    Class<E> getEventType();
}
