package eu.mshadeproduction.mwork.dispatcher;

public interface DispatcherContainer {

    <T> T getContainer(Class<T> aClass);

    <T> T getContainer(String key, Class<T> aClass);
}
