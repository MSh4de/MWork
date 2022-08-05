package eu.mshade.mwork.mediator;

import java.util.function.Supplier;

public interface Mediator<S> {

    Mediator NONE = new Mediator() {
        @Override
        public Object notify(Object source, Supplier callback, String key, Object[] args) {
            return callback.get();
        }
    };

    <T> T notify(S source, Supplier<T> callback, String key, Object[] args);

    default void notify(S source, Runnable runnable, String key, Object[] args) {
        notify(source, () -> {
            runnable.run();
            return null;
        }, key, args);
    }


}
