package eu.mshadeproduction.mwork.dispatcher;

public interface DispatcherDriver<T> {

    void register(DispatcherListener dispatcherListener);

    void unregister(DispatcherListener dispatcherListener);

    void dispatch(T t);

}
