package eu.mshadeproduction.mwork.dispatcher;

public interface DispatcherDriver<T> {

    void register(DispatcherListener<? extends T> dispatcherListener);

    void unregister(DispatcherListener<? extends T> dispatcherListener);

    void dispatch(T t, DispatcherContainer dispatcherContainer);

    void dispatch(T t);

}
