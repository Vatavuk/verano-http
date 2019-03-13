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
package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.RequestUri;
import hr.com.vgv.verano.http.response.ReasonPhrase;
import hr.com.vgv.verano.http.response.Status;
import hr.com.vgv.verano.http.wire.apache.ApacheContext;
import hr.com.vgv.verano.http.wire.apache.ApacheHeaders;
import hr.com.vgv.verano.http.wire.apache.ApacheRequest;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Apache http wire.
 * @since 1.0
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public class ApacheWire implements Wire {
    /**
     * Apache contexts.
     */
    private final Iterable<ApacheContext> contexts;

    /**
     * Additional parameters.
     */
    private final Dict parameters;

    /**
     * Ctor.
     * @param uri Uri
     */
    public ApacheWire(final String uri) {
        this(uri, new IterableOf<>());
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param inputs Inputs
     */
    public ApacheWire(final String uri, final DictInput... inputs) {
        this(uri, new IterableOf<>(), new DictOf(inputs));
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param contexts Apache contexts
     */
    public ApacheWire(final String uri, final ApacheContext... contexts) {
        this(uri, new IterableOf<>(contexts));
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param contexts Apache contexts
     */
    public ApacheWire(
        final String uri, final Iterable<ApacheContext> contexts
    ) {
        this(contexts, new DictOf(new RequestUri(uri)));
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param contexts Apache contexts
     * @param parameters Additional parameters
     */
    public ApacheWire(
        final String uri,
        final Iterable<ApacheContext> contexts,
        final Dict parameters
    ) {
        this(
            contexts,
            new HashDict(new JoinedDict(new RequestUri(uri), parameters))
        );
    }

    /**
     * Ctor.
     * @param contexts Apache contexts
     * @param parameters Additional parameters.
     */
    public ApacheWire(
        final Iterable<ApacheContext> contexts, final Dict parameters
    ) {
        this.contexts = contexts;
        this.parameters = parameters;
    }

    @Override
    public final Dict send(final Dict message) throws IOException {
        final Dict request = new JoinedDict(message, this.parameters);
        HttpClientBuilder builder = HttpClients.custom();
        final URI uri = new RequestUri.Of(request).uri();
        for (final ApacheContext context : this.contexts) {
            builder = context.apply(uri, builder);
        }
        try (CloseableHttpResponse response = builder.build()
            .execute(new ApacheRequest(request).value())) {
            final StatusLine status = response.getStatusLine();
            return new DictOf(
                new Status(status.getStatusCode()),
                new ReasonPhrase(status.getReasonPhrase()),
                new Body(fetchBody(response)),
                new ApacheHeaders(response.getAllHeaders())
            );
        }
    }

    /**
     * Fetch body from http response.
     * @param response Http response
     * @return Body Body
     */
    private String fetchBody(final CloseableHttpResponse response)
    {
        final HttpEntity entity = response.getEntity();
        return new UncheckedScalar<>(
            new Ternary<>(
                () -> entity != null,
                () -> EntityUtils.toString(entity),
                () -> ""
            )
        ).value();
    }
}
