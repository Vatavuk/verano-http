package hr.com.vgv.verano.http;

import java.util.Map;

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.Mapped;
import org.cactoos.list.Sticky;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.UncheckedScalar;

public class HashDict extends IterableEnvelope<Kvp> implements Dict
{
    private final Map<String, String> map;

    public HashDict(final Kvp... kvps)
    {
        this(new IterableOf<>(kvps));
    }

    public HashDict(final Iterable<Kvp> kvps)
    {
        this(
            kvps,
            new MapOf<>(
                new Mapped<>(
                    input -> new MapEntry<>(input.key(), input.value()),
                    kvps
                )
            )
        );
    }

    public HashDict(final Iterable<Kvp> kvps, final Map<String, String> map)
    {
        super(() -> new Sticky<>(kvps));
        this.map = map;
    }

    @Override
    public String get(String key)
    {
        if (this.contains(key))
        {
            return this.map.get(key);
        }
        throw new IllegalStateException(
            String.format("Key %s not found in dict", key)
        );
    }

    @Override
    public String get(String key, String def)
    {
        return new UncheckedScalar<>(
            new Ternary<>(
                () -> this.contains(key),
                () -> key,
                () -> def
            )
        ).value();
    }

    @Override
    public boolean contains(String key)
    {
        return this.map.containsKey(key);
    }
}
