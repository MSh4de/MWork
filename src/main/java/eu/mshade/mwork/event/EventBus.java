package eu.mshade.mwork.event;

import eu.mshade.mwork.ParameterContainer;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class EventBus<T> {

    private final Queue<EventContext<T>> eventContexts = new ConcurrentLinkedQueue<>();

    public <E extends T> EventContext<E> subscribe(Class<E> eventClass, EventListener<E> eventListener){
        DefaultEventContext<E> eventContext = new DefaultEventContext<>(eventClass, eventListener);
        eventContexts.add((DefaultEventContext<T>) eventContext);
        return eventContext;
    }

    public <E extends T> void publish(E event){
        this.publish(event, ParameterContainer.EMPTY);
    }

    public <E extends T> void publish(E event, ParameterContainer parameterContainer){
        List<EventContext<T>> eventContexts = this.eventContexts.stream()
                .filter(eventContext -> hasMatch(eventContext.getEventType(), (eventContext.getEventType().isInterface() && event.getClass().getInterfaces().length != 0 ? event.getClass().getInterfaces()[0] : event.getClass()), eventContext.getEventFilter()))
                .collect(Collectors.toList());
        if (!eventContexts.isEmpty()){
            for (EventPriority eventPriority : EventPriority.values()) {
                onEvent(event, parameterContainer, eventPriority, eventContexts);
            }
        }
    }

    public <E extends T> void publishAsync(E event){
        this.publishAsync(event, ParameterContainer.EMPTY);
    }

    public <E extends T> void publishAsync(E event, ParameterContainer parameterContainer) {
        CompletableFuture.runAsync(() -> publish(event, parameterContainer));
        //publish(event, parameterContainer);
    }


    private <E extends T> void onEvent(E event, ParameterContainer eventContainer, EventPriority eventPriority, List<EventContext<T>> eventContexts){
        //eventContexts.stream().filter(eventContext -> eventContext.getEventPriority() == eventPriority).forEach(eventContext -> scheduledExecutorService.execute(() -> eventContext.getEventListener().onEvent(event, eventContainer)));
        eventContexts.stream().filter(eventContext -> eventContext.getEventPriority() == eventPriority).forEach(eventContext -> eventContext.getEventListener().onEvent(event, eventContainer));
    }

    public <E extends T> List<EventContext<T>> getEventContexts(Class<E> eventType, EventFilter eventFilter){
        return eventContexts.stream().filter(eventContext -> hasMatch(eventContext.getEventType(), eventType, eventFilter)).collect(Collectors.toList());
    }

    private  boolean hasMatch(Class<?> from, Class<?> to, EventFilter eventFilter){
        return (eventFilter == EventFilter.ONLY ? from == to : from.isAssignableFrom(to));
    }

}
