package hr.com.vgv.verano.http;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import hr.com.vgv.verano.http.request.RequestUri;

public final class RequestUriOfTest
{
    @Test
    public void extractsUriFromDict() {
        final String base = "http://localhost:8080";
        final String path = "/example";
        MatcherAssert.assertThat(
            new RequestUri.Of(
                new HashDict(
                    new KvpOf("uri", base), new KvpOf("path", path)
                )
            ).uri().toString(),
            new IsEqual<>(URI.create(base + path).toString())
        );
    }
}
