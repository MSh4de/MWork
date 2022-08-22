package eu.mshade.mwork.event

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * The event bus manages listeners and allows you to trigger your own events.
 * @see [subscribe] to register a listener.
 * @see [publish] to trigger an event.
 * @author Luke and Oleksandr
 * @since 0.0.1
 */
class EventBus<T> {
    private val eventContexts: PriorityQueue<EventContext<*>> = PriorityQueue<EventContext<*>>(EventComparator())

    /**
     * Register a listener of an event.
     * @param eventClass the event class to listen to
     * @param eventListener your listener
     * @return An [EventContext] to manage listening options
     */
    fun <E : T> subscribe(eventClass: Class<E>, eventListener: EventListener<E>): EventContext<E> {
        val eventContext = DefaultEventContext(eventClass, eventListener)
        eventContexts.add(eventContext)
        return eventContext
    }

    /**
     * Allows you to unsubscribe any registered listener.
     * @param eventClass The event type of the listener
     * @param eventListener The listener to unsubscribe
     */
    fun <E : T> unsubscribe(eventClass: Class<E>, eventListener: EventListener<E>) {
        eventContexts.removeIf { it.eventType == eventClass && it.eventListener == eventListener }
    }

    /**
     * Pass an instance of an event to trigger all subscribed listeners.
     * @param event The event to trigger.
     */
    fun <E : T> publish(event: E) {
        eventContexts.stream()
            // Filter out contexts that don't match the event type or any subtype (EventFilter logic).
            .filter { eventContext -> hasMatch(
                eventContext.eventType.javaClass,
                if(eventContext.eventType.isInterface) eventContext.eventType.javaClass else
                    eventContext.eventType.interfaces[0].javaClass, eventContext.eventFilter
            )}
                // Trigger the listener on each context.
            .forEach { eventContext -> eventContext.eventListener.onEvent(event) }
    }

    /**
     * Do the same as [publish] but asynchronously.
     */
    fun <E : T> publishAsync(event: E) {
        runBlocking {
            launch {
                publish(event)
            }
        }
    }

    fun <E : T> getEventContexts(eventType: Class<E>, eventFilter: EventFilter): List<EventContext<T>> {
        return eventContexts.stream().filter { hasMatch(it.eventType, eventType, eventFilter) }
            .toList() as List<EventContext<T>>
    }

    /**
     * Check whether from and to are equals or if from is a subtype of to<br>
     * depending on the eventFilter.
     * @param from The event's class
     * @param to The event's class or any sub interface of it
     * @param eventFilter The event's filter
     */
    private fun hasMatch(from: Class<*>, to: Class<*>, eventFilter: EventFilter): Boolean {
        return if (eventFilter === EventFilter.ONLY) from == to else from.isAssignableFrom(to)
    }

    /**
     * A comparator to sort listeners by priority.
     */
    class EventComparator : Comparator<EventContext<*>> {
        override fun compare(o1: EventContext<*>, o2: EventContext<*>): Int {
            return o1.eventPriority.weight - o2.eventPriority.weight
        }

    }
}