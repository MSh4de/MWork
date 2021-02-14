package eu.mshadeproduction.mwork.dispatcher;

import java.lang.reflect.Method;

public class DispatcherContext {

    private Method method;
    private DispatcherListener dispatcherListener;

    public DispatcherContext(Method method, DispatcherListener dispatcherListener) {
        this.method = method;
        this.dispatcherListener = dispatcherListener;
    }

    public Method getMethod() {
        return method;
    }

    public DispatcherListener getDispatcherListener() {
        return dispatcherListener;
    }
}
