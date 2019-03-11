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

import java.util.Iterator;

import org.cactoos.Scalar;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Dictionary.
 * @since 1.0
 */
public interface Dict extends Iterable<Kvp> {

    /**
     * Retrieve dictionary value.
     * @param key Key
     * @return Value Dictionary value
     */
    String get(String key);

    /**
     * Retrieve dictionary value or return default one if the specified
     * key doesn't exist in the dictionary.
     * @param key Dictionary key
     * @param def Default value
     * @return Value Dictionary value
     */
    String get(String key, String def);

    /**
     * Check if dictionary contains key.
     * @param key Dictionary key.
     * @return Boolean Boolean
     */
    boolean contains(String key);

    /**
     * Simple implementation of {@link Dict} used to simplify Dict decorators.
     */
    class Simple implements Dict {

        /**
         * Original dictionary.
         */
        private final UncheckedScalar<Dict> origin;

        /**
         * Ctor.
         * @param origin Original dictionary
         */
        public Simple(final Scalar<Dict> origin)
        {
            this.origin = new UncheckedScalar<>(new StickyScalar<>(origin));
        }

        @Override
        public final String get(final String key)
        {
            return this.origin.value().get(key);
        }

        @Override
        public final String get(final String key, final String def)
        {
            return this.origin.value().get(key, def);
        }

        @Override
        public final boolean contains(final String key)
        {
            return this.origin.value().contains(key);
        }

        @Override
        public final Iterator<Kvp> iterator()
        {
            return this.origin.value().iterator();
        }
    }
}
