package eu.mshade.mwork.event

import eu.mshade.mwork.ParameterContainer

@FunctionalInterface
interface EventListener<E> {

    fun onEvent(event: E, eventContainer: ParameterContainer)

}