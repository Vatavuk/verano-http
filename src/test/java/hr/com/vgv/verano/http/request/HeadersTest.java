package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.parts.Header;
import hr.com.vgv.verano.http.parts.Headers;
import java.util.List;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public final class HeadersTest
{
    @Test
    public void buildHeaderInput()
    {
        final List<Kvp> kvps = new ListOf<>(
            new Headers(
                new Header("Content-Type", "application/json"),
                new Header("Authorization", "Bearer 1234")
            ).apply(new HashDict())
        );
        final Kvp content = kvps.get(0);
        MatcherAssert.assertThat(
            content.key(),
            new IsEqual<>("h.Content-Type")
        );
        MatcherAssert.assertThat(
            content.value(),
            new IsEqual<>("application/json")
        );
        final Kvp auth = kvps.get(1);
        MatcherAssert.assertThat(
            auth.key(),
            new IsEqual<>("h.Authorization")
        );
        MatcherAssert.assertThat(
            auth.value(),
            new IsEqual<>("Bearer 1234")
        );
    }
}
