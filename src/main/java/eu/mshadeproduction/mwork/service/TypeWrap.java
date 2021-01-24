package eu.mshadeproduction.mwork.service;
@FunctionalInterface
public interface TypeWrap<T, R> {

    T get(R r);

}
