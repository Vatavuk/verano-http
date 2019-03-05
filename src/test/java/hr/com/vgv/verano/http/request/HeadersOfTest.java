package hr.com.vgv.verano.http.request;

import java.util.List;

import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public final class HeadersOfTest
{
    @Test
    public void extractHeadersFromDict() {
        final List<Kvp> kvps = new ListOf<>(
            new Headers.Of(
                new HashDict(
                    new KvpOf("test", "test"),
                    new KvpOf("h.kfirst", "first"),
                    new KvpOf("h.ksecond", "second")
                )
            )
        );
        MatcherAssert.assertThat(
            kvps.size(),
            new IsEqual<>(2)
        );
        MatcherAssert.assertThat(
            kvps.get(0).key(),
            new IsEqual<>("kfirst")
        );
        MatcherAssert.assertThat(
            kvps.get(1).key(),
            new IsEqual<>("ksecond")
        );
    }
}
