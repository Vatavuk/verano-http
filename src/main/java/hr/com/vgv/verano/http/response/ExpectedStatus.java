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
package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Verification;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.RequestUri;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.cactoos.Text;
import org.cactoos.collection.CollectionOf;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * Assertion on http status.
 *
 * Example of usage:
 *
 * <pre>
 *  new Response(
 *      "http://example.com",
 *      new Get(
 *          "/items",
 *          new ExpectedStatus(200, new FailWith("error fetching example.com"))
 *      )
 *  ).touch();
 * </pre>
 *
 * @since 1.0
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public class ExpectedStatus implements Verification {
    /**
     * Statuses.
     */
    private final Iterable<Integer> statuses;

    /**
     * Error message.
     */
    private final UncheckedText message;

    /**
     * Ctor.
     * @param status Status
     * @param message Error message
     */
    public ExpectedStatus(final int status, final Text message) {
        this(new IterableOf<>(status), message);
    }

    /**
     * Ctor.
     * @param status Status
     * @param additional Additional statuses
     */
    public ExpectedStatus(final Integer status, final Integer... additional) {
        this(
            new Joined<>(status, new IterableOf<>(additional)), new TextOf("")
        );
    }

    /**
     * Ctor.
     * @param statuses Statuses
     * @param message Error message
     */
    public ExpectedStatus(
        final Iterable<Integer> statuses, final Text message
    ) {
        this.statuses = statuses;
        this.message = new UncheckedText(message);
    }

    @Override
    public final void verify(final Dict response) {
        final int status = new Status.Of(response).intValue();
        if (!new CollectionOf<>(this.statuses).contains(status)) {
            final String msg = String.format(
                "%s\n%s",
                this.message.asString(),
                String.format(
                    //@checkstyle LineLengthCheck (1 lines)
                    "Received response with status %d, instead of %d.\nReason: %s\nUrl: %s\nBody: %s",
                    status,
                    this.statuses.iterator().next(),
                    new ReasonPhrase.Of(response).asString(),
                    new RequestUri.Of(response).asString(),
                    new Body.Of(response).asString()
                )
            );
            throw new UncheckedIOException(new IOException(msg));
        }
    }
}
