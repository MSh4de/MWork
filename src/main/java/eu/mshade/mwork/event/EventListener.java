package eu.mshade.mwork.event;

import eu.mshade.mwork.ParameterContainer;

@FunctionalInterface
public interface EventListener<E> {

    void onEvent(E event, ParameterContainer eventContainer);

}
