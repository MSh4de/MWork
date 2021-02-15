package eu.mshadeproduction.mwork.dispatcher;

import java.lang.reflect.Method;

public class DispatcherContext {

    private Method method;
    private DispatcherListener dispatcherListener;
    private Class<?> genericClass;

    public DispatcherContext(Method method, DispatcherListener dispatcherListener) {
        this.method = method;
        this.dispatcherListener = dispatcherListener;
        for (Class<?> parameterType : this.method.getParameterTypes()) {
            if (!DispatcherContainer.class.isAssignableFrom(parameterType)) genericClass = parameterType;
        }
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getGenericClass() {
        return genericClass;
    }

    public DispatcherListener getDispatcherListener() {
        return dispatcherListener;
    }
}
