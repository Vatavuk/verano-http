package hr.com.vgv.verano.http;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import hr.com.vgv.verano.http.request.Path;

public final class PathTest
{
    @Test
    public void buildsPath() {
        String value = "/localhost";
        final Path path = new Path(value);
        MatcherAssert.assertThat(
            path.key(),
            new IsEqual<>("path")
        );
        MatcherAssert.assertThat(
            path.value(),
            new IsEqual<>(value)
        );
    }
}
