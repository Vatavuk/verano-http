package hr.com.vgv.verano.http;

import java.util.Map;
import java.util.function.Supplier;

import org.cactoos.map.MapEntry;

public class MapEntryEnvelope<K, V> implements Map.Entry<K, V>
{
    private final Supplier<Map.Entry<K,V>> origin;

    public MapEntryEnvelope(Map.Entry<K, V> origin)
    {
        this(() -> origin);
    }

    public MapEntryEnvelope(K key, Map<K, V> map) {
        this(() -> {
            if (map.containsKey(key)) {
                return new MapEntry<>(key, map.get(key));
            }
            throw new IllegalStateException(
                String.format("%s not found in map", key)
            );
        });
    }

    public MapEntryEnvelope(Supplier<Map.Entry<K,V>> origin) {
        this.origin = origin;
    }

    @Override
    public K getKey()
    {
        return this.origin.get().getKey();
    }

    @Override
    public V getValue()
    {
        return this.origin.get().getValue();
    }

    @Override
    public V setValue(V value)
    {
        throw new UnsupportedOperationException("#setValue");
    }
}
