package hr.com.vgv.verano.http;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import hr.com.vgv.verano.http.request.RequestUri;

public class RequestUriTest
{
    @Test
    public void buildsRequestUri() {
        final String value = "http://example.com";
        final RequestUri uri = new RequestUri(value);
        MatcherAssert.assertThat(
            uri.key(),
            new IsEqual<>("uri")
        );
        MatcherAssert.assertThat(
            uri.value(),
            new IsEqual<>(value)
        );
    }
}
