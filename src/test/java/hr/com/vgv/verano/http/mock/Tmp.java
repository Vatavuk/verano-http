package hr.com.vgv.verano.http.mock;

import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.request.Get;
import hr.com.vgv.verano.http.response.ExpectedStatus;
import hr.com.vgv.verano.http.response.Response;
import hr.com.vgv.verano.http.response.Status;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Tmp {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void tmp() {
        stubFor(get(urlEqualTo("/items"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("<response>Some content</response>")));

        Wire wire = new MockWire(
            new Get(
                "/items",
                new Status(200),
                new Body("some message")
            )
        );

        this.executeRequest(wire);

        WireMock.verify(
            getRequestedFor(urlEqualTo("/items"))
            .withRequestBody(equalTo("hello")));

        throw new AssertionError("\nexpected:<GET\n /items\n but was:<GET \n sth");
            //.withHeader("Content-Type", notMatching("application/json")));
    }

    private void executeRequest(final Wire wire) {
        new Response(
            wire,
            new Get(
                new Body("hello")
            ),
            new ExpectedStatus(200)
        ).touch();
    }
}
