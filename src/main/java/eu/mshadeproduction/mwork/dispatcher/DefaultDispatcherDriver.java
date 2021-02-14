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
            if (method.isAnnotationPresent(DispatcherHandler.class)) {
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
    public void dispatch(T t, DispatcherContainer dispatcherContainer) {
        for (DispatcherContext dispatcherContext : dispatcherContexts) {
            Object[] objects = buildParameter(dispatcherContext.getMethod(), t, dispatcherContainer);
            try {
                dispatcherContext.getMethod().invoke(dispatcherContext.getDispatcherListener(), objects);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void dispatch(T t) {
        dispatch(t, null);
    }


    private Object[] buildParameter(Method method, T t, DispatcherContainer dispatcherContainer){
        Object[] objects = new Object[method.getParameterTypes().length];
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            Class<?> parameterType = method.getParameterTypes()[i];
            if (t != null && parameterType.isAssignableFrom(t.getClass())) objects[i] = t;
            else if (dispatcherContainer != null && parameterType.isAssignableFrom(dispatcherContainer.getClass())) objects[i] = dispatcherContainer;
        }
        return objects;
    }
}
