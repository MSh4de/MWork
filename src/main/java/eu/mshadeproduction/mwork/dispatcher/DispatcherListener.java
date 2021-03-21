package eu.mshadeproduction.mwork.dispatcher;

@FunctionalInterface
public interface DispatcherListener<T> {

    void handle(T t, DispatcherContainer dispatcherContainer);

}
