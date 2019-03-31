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
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.KvpOf;
import org.cactoos.Text;

/**
 * Path in url.
 * @since 1.0
 */
public class Path extends DictInput.Envelope {
    /**
     * Path key in dictionary.
     */
    private static final String KEY = "path";

    /**
     * Ctor.
     * @param path Path
     */
    public Path(final String path) {
        super(
            dict -> new JoinedDict(
                dict,
                new HashDict(
                    new KvpOf(
                        Path.KEY,
                        String.format("%s%s", dict.get(Path.KEY, ""), path)
                    )
                )
            )
        );
    }

    /**
     * Path from dictionary.
     */
    public static class Of implements Text {
        /**
         * Dictionary.
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
            return this.dict.get(Path.KEY, "");
        }
    }
}
