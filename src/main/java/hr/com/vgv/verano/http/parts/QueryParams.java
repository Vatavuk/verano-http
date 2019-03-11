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
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;
import javax.ws.rs.core.UriBuilder;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;

/**
 * Http query parameters.
 * @since 1.0
 */
public class QueryParams extends DictInput.Simple {
    /**
     * Ctor.
     * @param params Parameters
     */
    public QueryParams(final QueryParam... params) {
        this(new IterableOf<>(params));
    }

    /**
     * Ctor.
     * @param params Parameters
     */
    public QueryParams(final Iterable<QueryParam> params) {
        super(
            (Dict dict) -> new DictOf(
                new Mapped<>(
                    input -> (DictInput) input,
                    params
                )
            )
        );
    }

    /**
     * Query string.
     * @param kvps Key-Value pairs
     * @return String Query string
     */
    private static String queryString(final Iterable<Kvp> kvps) {
        UriBuilder builder = UriBuilder.fromUri("");
        for (final Kvp kvp : kvps) {
            builder = builder.queryParam(kvp.key(), kvp.value());
        }
        return builder.build().toString();
    }

    /**
     * Query parameters from response.
     */
    public static class Of implements Text {

        /**
         * Response.
         */
        private final Dict response;

        /**
         * Ctor.
         * @param response Response
         */
        public Of(final Dict response) {
            this.response = response;
        }

        @Override
        public final String asString() {
            return QueryParams.queryString(
                new Mapped<>(
                    in -> new KvpOf(in.key().substring(2), in.value()),
                    new Filtered<>(
                        input -> input.key().startsWith("q."),
                        this.response
                    )
                )
            );
        }
    }
}
