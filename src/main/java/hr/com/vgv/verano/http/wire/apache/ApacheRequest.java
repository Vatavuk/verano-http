/**
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

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.cactoos.Scalar;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.Headers;
import hr.com.vgv.verano.http.parts.Method;
import hr.com.vgv.verano.http.parts.RequestUri;

/**
 * Apache http request.
 * @since 1.0
 */
public class ApacheRequest implements Scalar<HttpEntityEnclosingRequestBase>
{
    /**
     * Request as dictionary,
     */
    private final Dict request;

    /**
     * Ctor.
     * @param request Request
     */
    public ApacheRequest(final Dict request)
    {
        this.request = request;
    }

    @Override
    public final HttpEntityEnclosingRequestBase value() throws IOException
    {
        final HttpEntityEnclosingRequestBase result =
            new HttpEntityEnclosingRequestBase()
            {
                @Override
                public String getMethod()
                {
                    return new Method.Of(request).asString();
                }
            };
        final URI uri = new RequestUri.Of(this.request).uri();
        result.setConfig(
            RequestConfig.custom()
                .setCircularRedirectsAllowed(false)
                .setRedirectsEnabled(false)
                .build()
        );
        result.setURI(uri);
        result.setEntity(
            new BufferedHttpEntity(
                new InputStreamEntity(new Body.Of(this.request).stream())
            )
        );
        for (final Kvp header : new Headers.Of(this.request))
        {
            result.addHeader(header.key(), header.value());
        }
        return result;
    }
}
