package eu.mshade.mwork.event

import eu.mshade.mwork.ParameterContainer
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.stream.Collectors

class EventBus<T> {
    private val eventContexts: Queue<EventContext<T>> = ConcurrentLinkedQueue()
    fun <E : T> subscribe(eventClass: Class<E>, eventListener: EventListener<E>): EventContext<E> {
        val eventContext = DefaultEventContext(eventClass, eventListener)
        eventContexts.add(eventContext as DefaultEventContext<T>)
        return eventContext
    }

    fun <E : T?> publish(event: E) {
        this.publish(event, ParameterContainer.EMPTY)
    }

    fun <E : T?> publish(event: E, parameterContainer: ParameterContainer) {
        val eventContexts = eventContexts.stream()
            .filter { eventContext: EventContext<T> ->
                hasMatch(
                    eventContext.eventType,
                    if (eventContext.eventType.isInterface && event!!::class.java.interfaces.isNotEmpty()) event.javaClass.interfaces[0] else event!!::class.java,
                    eventContext.eventFilter
                )
            }
            .collect(Collectors.toList())
        if (eventContexts.isNotEmpty()) {
            /*
            TODO : Update this (currently contains errors)
            for (eventPriority in EventPriority.values()) {
                onEvent(event, parameterContainer, eventPriority, eventContexts)
            }*/
        }
    }

    fun <E : T?> publishAsync(event: E) {
        this.publishAsync(event, ParameterContainer.EMPTY)
    }

    fun <E : T?> publishAsync(event: E, parameterContainer: ParameterContainer) {
        CompletableFuture.runAsync { publish(event, parameterContainer) }
        //publish(event, parameterContainer);
    }

    private fun <E : T?> onEvent(
        event: E,
        eventContainer: ParameterContainer,
        eventPriority: EventPriority,
        eventContexts: List<EventContext<T>>
    ) {
        //eventContexts.stream().filter(eventContext -> eventContext.getEventPriority() == eventPriority).forEach(eventContext -> scheduledExecutorService.execute(() -> eventContext.getEventListener().onEvent(event, eventContainer)));
        eventContexts.stream().filter { eventContext: EventContext<T> -> eventContext.eventPriority === eventPriority }
            .forEach { eventContext: EventContext<T> -> eventContext.eventListener.onEvent(event!!, eventContainer) }
    }

    fun <E : T?> getEventContexts(eventType: Class<E>, eventFilter: EventFilter): List<EventContext<T>> {
        return eventContexts.stream()
            .filter { eventContext: EventContext<T> -> hasMatch(eventContext.eventType, eventType, eventFilter) }
            .collect(Collectors.toList())
    }

    private fun hasMatch(from: Class<*>, to: Class<*>, eventFilter: EventFilter): Boolean {
        return if (eventFilter === EventFilter.ONLY) from == to else from.isAssignableFrom(to)
    }
}