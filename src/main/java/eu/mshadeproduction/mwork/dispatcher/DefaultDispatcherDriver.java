package eu.mshadeproduction.mwork.dispatcher;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class DefaultDispatcherDriver<T> implements DispatcherDriver<T> {

    public Map<Class<?>, Queue<DispatcherListener<T>>> dispatcherListeners = new ConcurrentHashMap<>();
    private static final DispatcherContainer dispatcherContainerEmpty = DispatcherContainer.empty();


    @Override
    public <U extends T> void register(Class<U> aClass, DispatcherListener<U> dispatcherListener) {
        dispatcherListeners.computeIfAbsent(aClass, a -> new ConcurrentLinkedQueue<>()).add((DispatcherListener<T>) dispatcherListener);
    }

    @Override
    public void unregister(DispatcherListener<T> dispatcherListener) {
        dispatcherListeners.entrySet().stream()
                .filter(entry -> entry.getValue().contains(dispatcherListener))
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(aClass -> dispatcherListeners.get(aClass).remove(dispatcherListener));
    }

    @Override
    public <U extends T> void dispatch(U u, DispatcherContainer dispatcherContainer) {
        dispatcherListeners.computeIfAbsent(u.getClass(), a -> new ConcurrentLinkedQueue<>()).forEach(dispatcherListener -> dispatcherListener.handle(u, dispatcherContainer));
    }

    @Override
    public <U extends T>  void dispatch(U u) {
        dispatch(u, dispatcherContainerEmpty);
    }
}