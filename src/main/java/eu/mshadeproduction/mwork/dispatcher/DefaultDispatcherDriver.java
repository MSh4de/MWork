package eu.mshadeproduction.mwork.dispatcher;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DefaultDispatcherDriver<T> implements DispatcherDriver<T> {

    public Queue<DispatcherListener<T>> dispatcherListeners = new ConcurrentLinkedQueue<>();

    @Override
    public void register(DispatcherListener<? extends T> dispatcherListener) {
        dispatcherListeners.add((DispatcherListener<T>) dispatcherListener);
    }

    @Override
    public void unregister(DispatcherListener<? extends T> dispatcherListener) {
        dispatcherListeners.remove(dispatcherListener);
    }

    @Override
    public void dispatch(T t, DispatcherContainer dispatcherContainer) {
        dispatcherListeners.stream().filter(dispatcherListener -> dispatcherListener.getGenericClazz().isAssignableFrom(t.getClass())).forEach(dispatcherListener -> {
            dispatcherListener.handle(t, dispatcherContainer);
        });
    }

    @Override
    public void dispatch(T t) {
        dispatch(t, DispatcherContainerBuilder.builder().build());
    }
}