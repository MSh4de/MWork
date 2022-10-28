package eu.mshade.mwork.event

interface EventContext<E> {

    var eventPriority: EventPriority
    var eventFilter: EventFilter
    var eventType: Class<E>
    var eventListener: EventListener<E>

    fun withEventFilter(eventFilter: EventFilter) : EventContext<E>;
    fun withEventPriority(eventPriority: EventPriority) : EventContext<E>;
}