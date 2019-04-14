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
 * Http header.
 * @since 1.0
 */
public class Header extends DictInput.Envelope {

    /**
     * Ctor.
     * @param key Key
     * @param value Value
     */
    public Header(final String key, final String value) {
        super(new KvpOf(Header.buildKey(key), value));
    }

    /**
     * Build header key in dictionary.
     * @param key Key
     * @return Key Key
     */
    private static String buildKey(final String key) {
        return String.format("h.%s", Header.normalize(key));
    }

    /**
     * Normalize key.
     * Taken from https://github.com/jcabi/jcabi-http.
     * Author: Yegor Bugayenko (yegor@tpc2.com)
     * @param key The key to normalize
     * @return Normalized key
     */
    private static String normalize(final String key) {
        final char[] chars = key.toCharArray();
        chars[0] = Header.upper(chars[0]);
        for (int pos = 1; pos < chars.length; ++pos) {
            if (chars[pos - 1] == '-') {
                chars[pos] = Header.upper(chars[pos]);
            }
        }
        return new String(chars);
    }

    /**
     * Convert char to upper case, if required.
     * Taken from https://github.com/jcabi/jcabi-http.
     * Author: Yegor Bugayenko (yegor@tpc2.com)
     * @param chr The char to convert
     * @return Upper-case char
     */
    private static char upper(final char chr) {
        final char upper;
        if (chr >= 'a' && chr <= 'z') {
            upper = (char) (chr - ('a' - 'A'));
        } else {
            upper = chr;
        }
        return upper;
    }

    /**
     * Header from dictionary.
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
        public final String asString() {
            return this.dict.get(Header.buildKey(this.key));
        }
    }
}
