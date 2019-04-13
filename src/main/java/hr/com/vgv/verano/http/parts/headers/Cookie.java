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
package hr.com.vgv.verano.http.parts.headers;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.parts.Header;
import hr.com.vgv.verano.http.parts.Headers;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;
import org.cactoos.Text;

/**
 * Cookie header.
 * @since 1.0
 */
public class Cookie extends Header {
    /**
     * Ctor.
     * @param value Header
     */
    public Cookie(final String value) {
        super("Cookie", value);
    }

    /**
     * Cookie from dictionary.
     */
    public static class Of implements Text {

        /**
         * Cookie name.
         */
        private final String name;

        /**
         * Dictionary.
         */
        private final Dict dict;

        /**
         * Ctor.
         * @param name Cookie name
         * @param dict Dictionary
         */
        public Of(final String name, final Dict dict) {
            this.name = name;
            this.dict = dict;
        }

        @Override
        public final String asString() {
            final Map<String, List<String>> headers =
                new Headers.Of(this.dict).asMap();
            final String key = "Set-Cookie";
            if (headers.containsKey(key)) {
                for (final String header : headers.get(key)) {
                    //@checkstyle LineLength (1 line)
                    for (final HttpCookie candidate : HttpCookie.parse(header)) {
                        if (candidate.getName().equals(this.name)) {
                            return candidate.getValue();
                        }
                    }
                }
                throw new IllegalStateException(
                    String.format(
                        "Cookie %s not found in Set-Cookie header.",
                        this.name
                    )
                );
            }
            throw new IllegalStateException("Set-Cookie header not found.");
        }
    }
}
