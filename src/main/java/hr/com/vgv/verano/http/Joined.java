package hr.com.vgv.verano.http;

import java.util.Map;

import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;

public class Joined<K, V> extends MapEnvelope<K, V>
{
    public Joined(Map.Entry<K, V> entry, Map<K, V> map) {
        this(new MapOf<>(entry), map);
    }

    @SafeVarargs
    public Joined(Map<K, V>... maps)
    {
        super(
            () -> new MapOf<>(
                new org.cactoos.iterable.Joined<>(
                    new Mapped<>(
                        input -> input.entrySet(),
                        maps
                    )
                )
            )
        );
    }
}
