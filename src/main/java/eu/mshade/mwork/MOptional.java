package eu.mshade.mwork;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class MOptional<T> {

    private final T value;
    private Consumer<Exception> exceptionConsumer;

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

    public MOptional<T> ifPresent(MConsumer<? super T> consumer) {
        if (value != null){
            try {
                consumer.accept(value);
            }catch (Exception e){
                exceptionConsumer.accept(e);
            }
        }
        return this;
    }
    public <S> S ifPresent(MFunction<T, S> consumer, S other) {
        if (value != null) {
            try {
                return consumer.apply(value);
            }catch (Exception e){
                exceptionConsumer.accept(e);
            }
        }
        return other;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public MOptional<T> ifNotPresent(MConsumer<Void> consumer)  {
        if (value == null){
            try {
                consumer.accept(null);
            }catch (Exception e){
                exceptionConsumer.accept(e);
            }
        }
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

    public MOptional<T> exception(Consumer<Exception> consumer){
        this.exceptionConsumer = consumer;
        return this;
    }

    @Override
    public String toString() {
        return "MOptional{" +
                "value=" + value +
                '}';
    }
}
