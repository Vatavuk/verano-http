package hr.com.vgv.verano.http;

import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public final class JoinedMapTest
{
    @Test
    public void joinsMaps() {
        MatcherAssert.assertThat(
            new JoinedMap<>(
                new MapOf<>(
                    new MapEntry<>("first", "value1")
                ),
                new MapOf<>(
                    new MapEntry<>("second", "value2")
                )
            ),
            new IsEqual()
        );
    }
}
