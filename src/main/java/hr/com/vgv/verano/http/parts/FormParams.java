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
import hr.com.vgv.verano.http.KvpOf;
import java.net.URLEncoder;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.JoinedText;
import org.cactoos.text.UncheckedText;

/**
 * Http form parameters for Content-Type application/x-www-form-urlencoded.
 * @since 1.0
 */
public class FormParams extends DictInput.Envelope {
    /**
     * Ctor.
     * @param params Parameters
     */
    public FormParams(final FormParam... params) {
        this(new IterableOf<>(params));
    }

    /**
     * Ctor.
     * @param params Parameters
     */
    public FormParams(final Iterable<FormParam> params) {
        super(
            new DictOf(
                new Mapped<>(
                    input -> (DictInput) input,
                    params
                )
            )
        );
    }

    /**
     * Form parameters (x-www-form-urlencoded) from dictionary.
     */
    public static class Of implements Text {

        /**
         * Response.
         */
        private final Dict dict;

        /**
         * Ctor.
         * @param dict Response
         */
        public Of(final Dict dict) {
            this.dict = dict;
        }

        @Override
        public final String asString() {
            return new UncheckedText(
                new JoinedText(
                    "&",
                    new Mapped<>(
                        input -> String.format(
                            "%s=%s", input.key(),
                            URLEncoder.encode(
                                input.value(),
                                "UTF-8"
                            )
                        ),
                        new Mapped<>(
                            in -> new KvpOf(in.key().substring(2), in.value()),
                            new Filtered<>(
                                input -> input.key().startsWith("f."),
                                this.dict
                            )
                        )
                    )
                )
            ).asString();
        }
    }
}
