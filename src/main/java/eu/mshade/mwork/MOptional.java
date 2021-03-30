package eu.mshadeproduction.mwork;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class MOptional<T> {

    private final T value;

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
    public <S> S ifPresent(MConsumer<S,T> consumer, S other) {
        if (value != null) return consumer.accept(value);
        return other;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public MOptional<T> ifNotPresent(Runnable runnable) {
        if (value == null)
            runnable.run();
        return this;
    }

    public<U> MOptional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return MOptional.ofNullable(mapper.apply(value));
        }
    }


    @Override
    public String toString() {
        return "MOptional{" +
                "value=" + value +
                '}';
    }
}
