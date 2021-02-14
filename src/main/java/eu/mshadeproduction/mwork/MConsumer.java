package eu.mshadeproduction.mwork;

@FunctionalInterface
public interface MConsumer<S,T> {

    S accept(T t);

}
