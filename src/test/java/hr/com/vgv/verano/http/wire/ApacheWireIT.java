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

import hr.com.vgv.verano.http.mock.MockWire;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.JsonBody;
import hr.com.vgv.verano.http.parts.headers.ContentType;
import hr.com.vgv.verano.http.request.Delete;
import hr.com.vgv.verano.http.request.Get;
import hr.com.vgv.verano.http.request.Post;
import hr.com.vgv.verano.http.response.ExpectedStatus;
import hr.com.vgv.verano.http.response.FailWith;
import hr.com.vgv.verano.http.response.Response;
import hr.com.vgv.verano.http.wire.apache.BasicAuth;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * Integration test case for {@link ApacheWire}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public final class ApacheWireIT {

    /**
     * Wiremock rule.
     */
    @Rule
    public final WireMockRule mock = new WireMockRule(
        WireMockConfiguration.wireMockConfig().dynamicPort().dynamicHttpsPort()
    );

    @Test
    @Ignore
    public void postReq() {
        new JsonBody.Of(
            new Response(
                "http://example.com",
                new Post(
                    new Body("Hello World"),
                    new ContentType("text/html")
                )
            )
        ).json();
    }

    @Test
    public void googlePage() {
        new Response(
            new ApacheWire(
                "http://google.com",
                new BasicAuth("userame", "password")
            ),
            new ExpectedStatus(301, new FailWith("Cannot fetch from Google"))
        ).touch();
    }

    @Test
    public void sendsDeletesRequest() {
        this.mock.stubFor(WireMock.delete(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(204))
        );
        new Response(
            String.format("http://localhost:%d", this.mock.port()),
            new Delete("/items")
        ).touch();
        WireMock.verify(
            WireMock.deleteRequestedFor(WireMock.urlMatching("/item"))
                .withQueryParam("Content-Type", WireMock.equalTo("sth"))
        );
    }

    @Test
    public void sendsDeletesRequestV2() {
        MockWire wire = new MockWire(

        );
        sendRequest();
        wire.assertThat(
            new RequestEqualTo(

            )
            new Post(

            )
        );
    }

    public void sendRequest() {

    };
}
