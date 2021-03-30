package eu.mshadeproduction.mwork.dispatcher;

public interface DispatcherDriver<T> {

     <U extends T> void register(Class<U> aClass, DispatcherListener<U> dispatcherListener);

    void unregister(DispatcherListener<T> dispatcherListener);

    <U extends T> void dispatch(U u, DispatcherContainer dispatcherContainer);

    <U extends T> void dispatch(U u);

}
