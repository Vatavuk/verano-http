package hr.com.vgv.verano.http.request;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;

public final class RequestUriOfTest
{
    @Test
    public void extractsUriFromDict() {
        final String base = "http://localhost:8080";
        final String path = "/example";
        MatcherAssert.assertThat(
            new RequestUri.Of(
                new HashDict(
                    new KvpOf("uri", base),
                    new KvpOf("path", path),
                    new KvpOf("q.first", "10"),
                    new KvpOf("q.second", "someStr")
                )
            ).uri().toString(),
            new IsEqual<>(
                String.format(
                    "%s?first=10&second=someStr",
                    URI.create(base + path).toString()
                )
            )
        );
    }
}
