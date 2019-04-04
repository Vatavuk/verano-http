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
import hr.com.vgv.verano.http.KvpOf;
import org.cactoos.Text;

/**
 * Http query parameter.
 * @since 1.0
 */
public class QueryParam extends DictInput.Envelope {

    /**
     * Ctor.
     * @param key Key
     * @param value Value
     */
    public QueryParam(final String key, final String value) {
        super(new KvpOf(QueryParam.buildKey(key), value));
    }

    /**
     * Build query param key in dictionary.
     * @param key Key
     * @return Key Key
     */
    private static String buildKey(final String key) {
        return String.format("q.%s", key);
    }

    /**
     * Query parameter from dictionary.
     */
    public static class Of implements Text {

        /**
         * Parameter key.
         */
        private final String key;

        /**
         * Dictionary.
         */
        private final Dict dict;

        /**
         * Ctor.
         * @param key Key
         * @param dict Dictionary
         */
        public Of(final String key, final Dict dict) {
            this.key = key;
            this.dict = dict;
        }

        @Override
        public final String asString() throws Exception {
            return this.dict.get(QueryParam.buildKey(this.key));
        }
    }
}
