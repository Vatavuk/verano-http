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

import com.jcabi.log.Logger;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.parts.Method;
import hr.com.vgv.verano.http.parts.RequestUri;
import hr.com.vgv.verano.http.response.Status;
import java.net.HttpURLConnection;

/**
 * Wire that retries sending a request specified number of times before throwing
 * an exception.
 *
 * Example of usage:
 *
 * <pre>
 *    new Response(
 *       new RetryWire(
 *           new ApacheWire("https://example.com"), 5
 *       ),
 *       new Post(
 *           new Body("Hello World!")
 *       )
 *    ).touch();
 * </pre>
 *
 * @since 1.0
 */
@SuppressWarnings(
    {"PMD.AvoidCatchingGenericException", "PMD.GuardLogStatement"}
)
public class RetryWire implements Wire {

    /**
     * Original wire.
     */
    private final Wire origin;

    /**
     * Number of attempts before throwing an exception.
     */
    private final int attempts;

    /**
     * Ctor.
     * @param origin Original wire
     */
    public RetryWire(final Wire origin) {
        //@checkstyle MagicNumberCheck (1 lines)
        this(origin, 3);
    }

    /**
     * Ctor.
     * @param origin Original wire
     * @param attempts Number of attempts before throwing an exception
     */
    public RetryWire(final Wire origin, final int attempts) {
        this.origin = origin;
        this.attempts = attempts;
    }

    @Override
    public final Dict send(final Dict request) {
        int attempt = 0;
        final String method = new Method.Of(request).asString();
        final String uri = new RequestUri.Of(request).asString();
        while (attempt < this.attempts) {
            try {
                final Dict response = this.origin.send(request);
                final int status = new Status.Of(response).intValue();
                if (status < HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    return response;
                }
                Logger.warn(
                    this, "%s %s returns %d status (attempt #%d)",
                    method, uri, status, attempt + 1
                );
                //@checkstyle IllegalCatchCheck (1 lines)
            } catch (final Exception ex) {
                Logger.warn(
                    this, "%s: %s",
                    ex.getClass().getName(), ex.getLocalizedMessage()
                );
            }
            ++attempt;
        }
        throw new IllegalStateException(
            String.format("Failed after %d attempts", attempt)
        );
    }
}
