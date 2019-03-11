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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.cactoos.Text;

/**
 * Http Body.
 * @since 1.0
 */
public class Body extends DictInput.Simple {
    /**
     * Body key in dictionary.
     */
    private static final String KEY = "body";

    /**
     * Ctor.
     * @param body Body
     */
    public Body(final byte[] body) {
        this(new String(body, StandardCharsets.UTF_8));
    }

    /**
     * Ctor.
     * @param body Body
     */
    public Body(final String body) {
        super(new KvpOf(Body.KEY, body));
    }

    /**
     * Body from dictionary.
     */
    public static class Of implements Text {

        /**
         * Response.
         */
        private final Dict dict;

        /**
         * Ctor.
         * @param dict Dictionary
         */
        public Of(final Dict dict) {
            this.dict = dict;
        }

        @Override
        public final String asString() {
            String body = new FormParams.Of(this.dict).asString();
            if (body.isEmpty()) {
                body = this.dict.get(Body.KEY, "");
            }
            return body;
        }

        /**
         * Body as stream.
         * @return InputStream Stream
         */
        public final InputStream stream() {
            return new ByteArrayInputStream(this.asString().getBytes());
        }
    }
}
