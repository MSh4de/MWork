package eu.mshade.mwork.event

interface EventPriorities {

    companion object {
        @JvmField val BY_WEIGHT: MutableMap<Int, EventPriority> = mutableMapOf()
        @JvmField val HIGH: EventPriority = EventPriority.from(1)
        @JvmField val NORMAL: EventPriority = EventPriority.from(2)
        @JvmField val LOW: EventPriority = EventPriority.from(3)

        @JvmStatic fun values(): List<EventPriority> = listOf(HIGH, NORMAL, LOW)

        init {
            values().forEach { priority -> BY_WEIGHT[priority.weight] = priority }
        }

        @JvmStatic fun byWeight(weight: Int): EventPriority = BY_WEIGHT[weight] ?: throw IllegalArgumentException("No priority with weight $weight")
    }
}