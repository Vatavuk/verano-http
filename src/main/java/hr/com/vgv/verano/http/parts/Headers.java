/*
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
package hr.com.vgv.verano.http.parts;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;

/**
 * Http headers.
 * @since 1.0
 */
public class Headers extends DictInput.Simple {

    /**
     * Ctor.
     * @param headers Headers
     */
    public Headers(final Header... headers) {
        this(new IterableOf<>(headers));
    }

    /**
     * Ctor.
     * @param headers Headers
     */
    public Headers(final Iterable<Header> headers) {
        super(
            (Dict dict) -> new DictOf(
                new Mapped<>(
                    input -> (DictInput) input,
                    headers
                )
            )
        );
    }

    /**
     * Headers from dictionary.
     */
    public static class Of extends IterableEnvelope<Kvp> {

        /**
         * Ctor.
         * @param dict Dictionary
         */
        public Of(final Dict dict) {
            super(() -> new Mapped<>(
                in -> new KvpOf(in.key().substring(2), in.value()),
                new Filtered<>(
                    input -> input.key().startsWith("h."),
                    dict
                )
            ));
        }

        /**
         * Map of headers.
         * @return Map Map
         */
        public final Map<String, List<String>> asMap() {
            final ConcurrentMap<String, List<String>> result =
                new ConcurrentHashMap<>(0);
            for (final Kvp header : this) {
                result.putIfAbsent(header.key(), new LinkedList<>());
                result.get(header.key()).add(header.value());
            }
            return result;
        }
    }
}
