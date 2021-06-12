package eu.mshade.mwork.event;


public class DefaultEventContext<E> implements EventContext<E> {

    private EventPriority eventPriority = EventPriority.NORMAL;
    private EventFilter eventFilter = EventFilter.ONLY;
    private Class<E> eventType;
    private EventListener<E> eventListener;

    public DefaultEventContext(Class<E> eventType, EventListener<E> eventListener) {
        this.eventType = eventType;
        this.eventListener = eventListener;
    }

    @Override
    public EventContext<E> withEventFilter(EventFilter eventFilter) {
        this.eventFilter = eventFilter;
        return this;
    }

    @Override
    public EventContext<E> withEventPriority(EventPriority eventPriority) {
        this.eventPriority = eventPriority;
        return this;
    }

    @Override
    public EventPriority getEventPriority() {
        return eventPriority;
    }

    @Override
    public EventFilter getEventFilter() {
        return eventFilter;
    }

    @Override
    public EventListener<E> getEventListener() {
        return eventListener;
    }

    @Override
    public Class<E> getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "DefaultEventContext{" +
                "eventPriority=" + eventPriority +
                ", eventFilter=" + eventFilter +
                ", eventType=" + eventType +
                ", eventListener=" + eventListener +
                '}';
    }
}
