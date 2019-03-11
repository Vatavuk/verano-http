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
package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.parts.Method;
import hr.com.vgv.verano.http.parts.Path;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

/**
 * Http request.
 * @since 1.0
 */
public class Request extends Dict.Simple {
    /**
     * Ctor.
     * @param method Http method
     * @param inputs Inputs
     */
    public Request(final String method, final DictInput... inputs) {
        this("", method, inputs);
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param method Http method
     * @param inputs Inputs
     */
    public Request(
        final String uri, final String method, final DictInput... inputs
    ) {
        this(uri, method, new IterableOf<>(inputs));
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param method Http method
     * @param inputs Inputs
     */
    public Request(
        final String uri, final String method, final Iterable<DictInput> inputs
    ) {
        super(
            () -> new DictOf(
                new Joined<DictInput>(
                    new IterableOf<>(
                        new Path(uri), new Method(method)
                    ),
                    inputs
                )
            )
        );
    }
}
