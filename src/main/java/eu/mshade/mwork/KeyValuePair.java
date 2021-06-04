package eu.mshade.mwork;

import java.util.Objects;

public class KeyValuePair<K, V> {

    private final K key;
    private final V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyValuePair)) return false;
        KeyValuePair<?, ?> that = (KeyValuePair<?, ?>) o;
        return key.equals(that.key) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "KeyValuePair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
