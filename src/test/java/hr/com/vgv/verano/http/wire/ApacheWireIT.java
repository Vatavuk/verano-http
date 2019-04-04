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

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.mock.MockAnswer;
import hr.com.vgv.verano.http.mock.MockWire;
import hr.com.vgv.verano.http.mock.PathMatch;
import hr.com.vgv.verano.http.mock.PostMatch;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.Path;
import hr.com.vgv.verano.http.parts.body.JsonBody;
import hr.com.vgv.verano.http.parts.headers.ContentType;
import hr.com.vgv.verano.http.request.Delete;
import hr.com.vgv.verano.http.request.Post;
import hr.com.vgv.verano.http.response.ExpectedStatus;
import hr.com.vgv.verano.http.response.FailWith;
import hr.com.vgv.verano.http.response.Response;
import hr.com.vgv.verano.http.wire.apache.BasicAuth;
import org.hamcrest.core.IsEqual;
import org.hamcrest.text.MatchesPattern;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 * Integration test case for {@link ApacheWire}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
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
                new BasicAuth("username", "password")
            ),
            new ExpectedStatus(301, new FailWith("Cannot fetch from Google"))
        ).touch();
    }

    @Test
    public void sendsDeleteRequest() {
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
    public void sendsDeletesRequestNew() {
        final MockWire wire = new MockWire(
            new MockAnswer(
                new PostMatch(
                    new PathMatch(
                        MatchesPattern.matchesPattern("/my/resource/[a-z0-9]+")
                    )
                ),
                new Response(
                    new Body("something"),
                    new ContentType("html/txt")
                )
            )
        );
        this.sendRequest(wire);
        wire.verify(
            new PostMatch(
                new PathMatch(new IsEqual<>("/my/resource/1"))
            )
        );
    }

    /**
     * Send request.
     * @param wire Wire
     */
    private void sendRequest(final Wire wire) {
        new Response(
            wire,
            new Post(
                new Path("/my/resource/1")
            )
        ).touch();
    }

}
