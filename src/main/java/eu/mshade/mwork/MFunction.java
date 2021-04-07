package eu.mshade.mwork;

@FunctionalInterface
public interface MFunction<T, R> {

    R apply(T t) throws Exception;

}
