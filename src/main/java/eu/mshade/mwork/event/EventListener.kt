package eu.mshade.mwork.event

@FunctionalInterface
interface EventListener<out E> {

    fun onEvent(event: @UnsafeVariance E)

}