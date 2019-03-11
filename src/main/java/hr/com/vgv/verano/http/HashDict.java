/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

/**
 * Dictionary implemented with HashMap.
 * @since 1.0
 */
public class HashDict extends IterableEnvelope<Kvp> implements Dict
{
    /**
     * Map.
     */
    private final Map<String, String> map;

    /**
     * Ctor.
     * @param kvps Key-value pairs
     */
    public HashDict(final Kvp... kvps)
    {
        this(new IterableOf<>(kvps));
    }

    /**
     * Ctor.
     * @param kvps Key-value pairs
     */
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

    /**
     * Ctor.
     * @param kvps Key-value pairs
     * @param map Map
     */
    public HashDict(final Iterable<Kvp> kvps, final Map<String, String> map)
    {
        super(() -> new Sticky<>(kvps));
        this.map = map;
    }

    @Override
    public final String get(final String key)
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
    public final String get(final String key, final String def)
    {
        return new UncheckedScalar<>(
            new Ternary<>(
                () -> this.contains(key),
                () -> this.map.get(key),
                () -> def
            )
        ).value();
    }

    @Override
    public final boolean contains(final String key)
    {
        return this.map.containsKey(key);
    }
}
