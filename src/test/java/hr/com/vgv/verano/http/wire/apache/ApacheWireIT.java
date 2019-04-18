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

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import hr.com.vgv.verano.http.parts.headers.Accept;
import hr.com.vgv.verano.http.parts.headers.ContentType;
import hr.com.vgv.verano.http.request.Delete;
import hr.com.vgv.verano.http.request.Get;
import hr.com.vgv.verano.http.request.Post;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case for {@link ApacheWire}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class ApacheWireIT {

    /**
     * Wiremock rule.
     */
    @Rule
    public final WireMockRule mock = new WireMockRule(
        WireMockConfiguration.wireMockConfig().dynamicPort().dynamicHttpsPort()
    );

    @Test
    public void sendsPostRequest() throws Exception {
        this.mock.stubFor(WireMock.post(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(201))
        );
        new ApacheWire(
            String.format("http://localhost:%d", this.mock.port())
        ).send(new Post("/items"));
        WireMock.verify(
            WireMock.postRequestedFor(WireMock.urlMatching("/items"))
        );
    }

    @Test
    public void sendsPutRequestWithHeaders() throws Exception {
        this.mock.stubFor(WireMock.post(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(204))
        );
        new ApacheWire(
            String.format("http://localhost:%d", this.mock.port()),
            new ContentType("application/json")
        ).send(new Post("/items"));
        WireMock.verify(
            WireMock.postRequestedFor(WireMock.urlMatching("/items"))
                .withHeader(
                    "Content-Type", WireMock.containing("application/json")
                )
        );
    }

    @Test
    public void sendsGetRequestWithHeaders() throws Exception {
        this.mock.stubFor(WireMock.get(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(200))
        );
        new ApacheWire(
            String.format("http://localhost:%d", this.mock.port()),
            new VrApacheClient(),
            new Accept("application/json")
        ).send(new Get("/items"));
        WireMock.verify(
            WireMock.getRequestedFor(WireMock.urlMatching("/items"))
                .withHeader("Accept", WireMock.containing("application/json"))
        );
    }

    @Test
    public void sendsDeleteRequest() throws Exception {
        this.mock.stubFor(WireMock.delete(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(204))
        );
        new ApacheWire(
            new VrApacheClient(),
            new Accept("application/json")
        ).send(
            new Delete(String.format("http://localhost:%d", this.mock.port()))
        );
        WireMock.verify(
            WireMock.deleteRequestedFor(WireMock.urlMatching("/"))
                .withHeader("Accept", WireMock.containing("application/json"))
        );
    }
}
