package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import java.util.Map;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sticky;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Dictionary based on HashMap.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @since 1.0
 */
public class HashDict extends IterableEnvelope<Kvp> implements Dict {

    private final Map<String, String> map;

    public HashDict(final Kvp... kvps) {
        this(new IterableOf<>(kvps));
    }

    public HashDict(final Iterable<Kvp> kvps) {
        this(
            kvps,
            new MapOf<>(
                new Mapped<>(
                    kvp -> new MapEntry<>(kvp.key(), kvp.value()),
                    kvps
                )
            )
        );
    }

    public HashDict(final Iterable<Kvp> kvps, final Map<String, String> map) {
        super(() -> new Sticky<>(kvps));
        this.map = map;
    }

    @Override
    public String get(final String key) {
        if (this.contains(key)) {
            return this.map.get(key);
        }
        throw new IllegalStateException(
            String.format("Key %s doesn't exist in dictionary.", key)
        );
    }

    @Override
    public String get(final String key, final String def) {
        return new UncheckedScalar<>(
            new Ternary<>(
                () -> this.contains(key),
                () -> this.map.get(key),
                () -> def
            )
        ).value();
    }

    @Override
    public boolean contains(final String key) {
        return this.map.containsKey(key);
    }
}
