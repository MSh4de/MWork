package eu.mshade.mwork.event

class EventPriority private constructor(val weight: Int) {

    companion object {
        fun from(weight: Int): EventPriority {
            return EventPriority(weight)
        }
    }
}