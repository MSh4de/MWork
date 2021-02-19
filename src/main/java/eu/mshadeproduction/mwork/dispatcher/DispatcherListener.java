package eu.mshadeproduction.mwork.dispatcher;

public interface DispatcherListener<T> {

    void handle(T t, DispatcherContainer dispatcherContainer);

    Class<T> getGenericClazz();

}
