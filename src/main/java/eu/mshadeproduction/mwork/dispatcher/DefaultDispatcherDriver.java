package eu.mshadeproduction.mwork.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DefaultDispatcherDriver<T> implements DispatcherDriver<T> {

    public Queue<DispatcherContext> dispatcherContexts = new ConcurrentLinkedQueue<>();

    @Override
    public void register(DispatcherListener dispatcherListener) {
        for (Method method : dispatcherListener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(DispatcherHandler.class) && method.getParameterTypes().length == 1) {
                dispatcherContexts.add(new DispatcherContext(method, dispatcherListener));
            }
        }
    }

    @Override
    public void unregister(DispatcherListener dispatcherListener) {
        dispatcherContexts.stream().filter(dispatcherContext -> dispatcherContext.getDispatcherListener() == dispatcherListener).findFirst().ifPresent(dispatcherContext -> {
            dispatcherContexts.remove(dispatcherContext);
        });
    }

    @Override
    public void dispatch(T t) {
        for (DispatcherContext dispatcherContext : dispatcherContexts) {
            if(dispatcherContext.getMethod().getParameterTypes()[0].isAssignableFrom(t.getClass())) {
                try {
                    dispatcherContext.getMethod().invoke(dispatcherContext.getDispatcherListener(), t);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
