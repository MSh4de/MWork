package eu.mshade.mwork.event

interface EventPriorities {

    companion object {
        val HIGH: EventPriority = EventPriority.from(3)
        val NORMAL: EventPriority = EventPriority.from(2)
        val LOW: EventPriority = EventPriority.from(1)
    }
}