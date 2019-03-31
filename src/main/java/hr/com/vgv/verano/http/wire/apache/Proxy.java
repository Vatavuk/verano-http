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

import java.net.URI;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

/**
 * Http proxy.
 * @since 1.0
 */
public class Proxy implements ApacheContext {

    /**
     * Scheme.
     */
    private final String scheme;

    /**
     * Host.
     */
    private final String host;

    /**
     * Port.
     */
    private final int port;

    /**
     * Ctor.
     * @param host Host
     * @param port Port
     */
    public Proxy(final String host, final int port) {
        this("http", host, port);
    }

    /**
     * Ctor.
     * @param scheme Scheme
     * @param host Host
     * @param port Port
     */
    public Proxy(final String scheme, final String host, final int port) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
    }

    @Override
    public final HttpClientBuilder apply(
        final URI uri, final HttpClientBuilder builder
    ) {
        return builder.setRoutePlanner(
            new DefaultProxyRoutePlanner(
                new HttpHost(this.host, this.port, this.scheme)
            )
        );
    }
}
