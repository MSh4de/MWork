package eu.mshade.mwork.event

interface EventPriorities {

    companion object {
        private val BY_WEIGHT: MutableMap<Int, EventPriority>
            get() = mutableMapOf()

        val HIGH: EventPriority = EventPriority.from(3)
        val NORMAL: EventPriority = EventPriority.from(2)
        val LOW: EventPriority = EventPriority.from(1)

        fun values(): List<EventPriority> = listOf(HIGH, NORMAL, LOW)

        init {
            values().forEach { priority -> BY_WEIGHT[priority.weight] = priority }
        }

        fun byWeight(weight: Int): EventPriority = BY_WEIGHT[weight] ?: throw IllegalArgumentException("No priority with weight $weight")
    }
}