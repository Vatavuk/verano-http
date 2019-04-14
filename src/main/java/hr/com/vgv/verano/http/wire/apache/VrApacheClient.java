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
package hr.com.vgv.verano.http.wire.apache;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.Headers;
import hr.com.vgv.verano.http.parts.Method;
import hr.com.vgv.verano.http.parts.RequestUri;
import java.io.IOException;
import java.net.URI;
import java.util.function.Supplier;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.cactoos.iterable.IterableOf;

/**
 * Apache client adapted to verano-http environment.
 * @since 1.0
 */
public class VrApacheClient implements ApacheClient {

    /**
     * Original http client.
     */
    private final Supplier<CloseableHttpClient> origin;

    /**
     * Http request configuration.
     */
    private final RequestConfig config;

    /**
     * Ctor.
     * @param contexts Contexts
     */
    public VrApacheClient(final ApacheContext... contexts) {
        this(new IterableOf<>(contexts));
    }

    /**
     * Ctor.
     * @param contexts Contexts
     */
    public VrApacheClient(final Iterable<ApacheContext> contexts) {
        this(() -> VrApacheClient.buildHttpClient(contexts));
    }

    /**
     * Ctor.
     * @param config Request config
     * @param contexts Contexts
     */
    public VrApacheClient(
        final RequestConfig config, final ApacheContext... contexts
    ) {
        this(config, new IterableOf<>(contexts));
    }

    /**
     * Ctor.
     * @param config Request config
     * @param contexts Contexts
     */
    public VrApacheClient(
        final RequestConfig config, final Iterable<ApacheContext> contexts
    ) {
        this(config, () -> VrApacheClient.buildHttpClient(contexts));
    }

    /**
     * Ctor.
     * @param origin Original http client
     */
    public VrApacheClient(final Supplier<CloseableHttpClient> origin) {
        this(
            RequestConfig.custom()
                .setCircularRedirectsAllowed(false)
                .setRedirectsEnabled(false)
                .build(),
            origin
        );
    }

    /**
     * Ctor.
     * @param config Request config
     * @param origin Original http client
     */
    public VrApacheClient(
        final RequestConfig config,
        final Supplier<CloseableHttpClient> origin
    ) {
        this.origin = origin;
        this.config = config;
    }

    @Override
    public final CloseableHttpResponse execute(
        final Dict request
    ) throws IOException {
        final HttpEntityEnclosingRequestBase base =
            new HttpEntityEnclosingRequestBase() {
                @Override
                public String getMethod() {
                    //@checkstyle RequireThisCheck (1 lines)
                    return new Method.Of(request).asString();
                }
            };
        final URI uri = new RequestUri.Of(request).uri();
        base.setConfig(this.config);
        base.setURI(uri);
        base.setEntity(new StringEntity(new Body.Of(request).asString()));
        for (final Kvp header : new Headers.Of(request)) {
            base.addHeader(header.key(), header.value());
        }
        return this.origin.get().execute(base);
    }

    /**
     * Build http client from provided context information.
     * @param contexts Apache contexts
     * @return Client Http client
     */
    private static CloseableHttpClient buildHttpClient(
        final Iterable<ApacheContext> contexts
    ) {
        HttpClientBuilder builder = HttpClients.custom();
        for (final ApacheContext context : contexts) {
            builder = context.apply(builder);
        }
        return builder.build();
    }
}
