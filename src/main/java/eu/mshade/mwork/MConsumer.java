package eu.mshade.mwork;

@FunctionalInterface
public interface MConsumer<T> {

    void accept(T t) throws Exception;
}
