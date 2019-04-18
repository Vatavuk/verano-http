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

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import hr.com.vgv.verano.http.parts.headers.Accept;
import hr.com.vgv.verano.http.request.Get;
import java.io.UncheckedIOException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;

/**
 * Integration test case for {@link Response}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class ResponseIT {

    /**
     * Wiremock rule.
     */
    @Rule
    public final WireMockRule mock = new WireMockRule(
        WireMockConfiguration.wireMockConfig().dynamicPort().dynamicHttpsPort()
    );

    @Test
    public void successfulResponse() {
        this.mock.stubFor(WireMock.get(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(200))
        );
        new Response(
            String.format("http://localhost:%d", this.mock.port())
        ).touch();
        WireMock.verify(
            WireMock.getRequestedFor(WireMock.urlPathEqualTo("/"))
        );
    }

    @Test(expected = UncheckedIOException.class)
    public void unsuccessfulResponse() {
        this.mock.stubFor(WireMock.get(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(204))
        );
        new Response(
            String.format("http://localhost:%d", this.mock.port()),
            new ExpectedStatus(200)
        ).touch();
    }

    @Test
    public void successfulResponseToRequestWithParameters() {
        this.mock.stubFor(WireMock.get(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(200))
        );
        new Response(
            String.format("http://localhost:%d", this.mock.port()),
            new Get(
                new Accept("application/json")
            )
        ).touch();
        WireMock.verify(
            WireMock.getRequestedFor(WireMock.urlPathEqualTo("/"))
        );
    }

    @Test(expected = UncheckedIOException.class)
    public void unsuccessfulResponseToRequestWithParameters() {
        this.mock.stubFor(WireMock.get(WireMock.urlMatching("/.*"))
            .willReturn(WireMock.aResponse().withStatus(205))
        );
        new Response(
            String.format("http://localhost:%d", this.mock.port()),
            new Get(
                new Accept("application/json")
            ),
            new ExpectedStatus(200)
        ).touch();
    }

    @Test
    public void buildsResponseFromInputs() {
        MatcherAssert.assertThat(
            new Response(
                new Status(200)
            ).get("status"),
            new IsEqual<>("200")
        );
    }
}
