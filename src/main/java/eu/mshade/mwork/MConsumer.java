package eu.mshade.mwork;

@FunctionalInterface
public interface MConsumer<S,T> {

    S accept(T t);

}
