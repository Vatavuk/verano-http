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

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

import org.cactoos.Text;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.parts.Header;
import hr.com.vgv.verano.http.parts.Headers;

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

    public static class Of implements Text {

        private final String key;

        private final Dict dict;

        public Of(String key, Dict dict)
        {
            this.key = key;
            this.dict = dict;
        }

        @Override
        public String asString()
        {
            final Map<String, List<String>> headers =
                new Headers.Of(this.dict).asMap();
            if (headers.containsKey("Cookie")) {

                for (final String header : headers.get("Cookie"))
                {
                    for (final HttpCookie candidate : HttpCookie.parse(header))
                    {
                        if (candidate.getName().equals(this.key))
                        {
                            cookie = RestResponse.cookie(candidate);
                            break;
                        }
                    }
                }
                return this.dict.;
            }
            throw new IllegalArgumentException("Cookie header not found.");
        }
    }
}
