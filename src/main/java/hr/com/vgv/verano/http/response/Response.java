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
package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.wire.ApacheWire;
import hr.com.vgv.verano.http.wire.AssertionWire;

/**
 * Http response.
 * @since 1.0
 */
public class Response extends Dict.Simple
{
    /**
     * Ctor.
     * @param uri Uri
     * @param assertion Assertion
     */
    public Response(final String uri, final Assertion assertion) {
        this(new ApacheWire(uri), assertion);
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param request Request
     */
    public Response(final String uri, final Dict request) {
        this(new ApacheWire(uri), request);
    }

    /**
     * Ctor.
     * @param uri Uri
     * @param request Request
     * @param assertion Assertion
     */
    public Response(
        final String uri, final Dict request, final Assertion assertion
    ) {
        this(new ApacheWire(uri), request, assertion);
    }

    /**
     * Ctor.
     * @param wire Wire
     */
    public Response(final Wire wire) {
        this(wire, new HashDict());
    }

    /**
     * Ctor.
     * @param wire Wire
     * @param assertion Assertion
     */
    public Response(final Wire wire, final Assertion assertion) {
        this(wire, new HashDict(), assertion);
    }

    /**
     * Ctor.
     * @param wire Wire
     * @param request Request
     */
    public Response(final Wire wire, final Dict request) {
        this(wire, request, in -> {});
    }

    /**
     * Ctor.
     * @param wire Wire
     * @param request Request
     * @param assertion Assertion
     */
    public Response(
        final Wire wire, final Dict request, final Assertion assertion
    ) {
        super(() -> new AssertionWire(wire, assertion).send(request));
    }

    /**
     * Trigger request through wire.
     */
    public final void touch() {
        this.iterator();
    }
}
