package eu.mshade.mwork.dispatcher;

@FunctionalInterface
public interface DispatcherListener<T> {

    void handle(T t, DispatcherContainer dispatcherContainer);

}
