package eu.mshadeproduction.mwork;


import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MOptional<T> {

    private T value;

    private MOptional(T value) {
        this.value = value;
    }

    public static <T> MOptional<T> of(T value) {
        return new MOptional<>(value);
    }

    public static <T> MOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public static<T> MOptional<T> empty() {
        return new MOptional<>(null);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public MOptional<T> ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
        return this;
    }

    public MOptional<T> ifNotPresent(Runnable runnable) {
        if (value == null)
            runnable.run();
        return this;
    }
}
