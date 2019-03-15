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
package hr.com.vgv.verano.http;

import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.func.UncheckedFunc;

/**
 * Input to be attached to a dictionary.
 * @since 1.0
 */
public interface DictInput {
    /**
     * Apply input to a dictionary.
     * @param dict Dictionary
     * @return Dict Dictionary
     */
    Dict apply(Dict dict);

    /**
     * Simple implementation of {@link DictInput} used
     * to simplify DictInput decorators.
     */
    class Envelope implements DictInput {
        /**
         * Original DictInput.
         */
        private final UncheckedFunc<Dict, Dict> origin;

        /**
         * Ctor.
         * @param input Dictionary input
         */
        public Envelope(final Scalar<DictInput> input) {
            this(
                (Dict dict) -> new JoinedDict(dict, new DictOf(input.value()))
            );
        }

        /**
         * Ctor.
         * @param kvps Key-value pairs
         */
        public Envelope(final Kvp... kvps) {
            this(new HashDict(kvps));
        }

        /**
         * Ctor.
         * @param dict Dictionary
         */
        public Envelope(final Dict dict) {
            this((Dict input) -> new JoinedDict(input, dict));
        }

        /**
         * Ctor.
         * @param origin Origin
         */
        public Envelope(final Func<Dict, Dict> origin) {
            this.origin = new UncheckedFunc<>(origin);
        }

        @Override
        public final Dict apply(final Dict dict) {
            return this.origin.apply(dict);
        }
    }
}
