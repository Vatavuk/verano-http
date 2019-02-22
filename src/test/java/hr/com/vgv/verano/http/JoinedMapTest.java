package hr.com.vgv.verano.http;

import java.util.Map;

import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public final class JoinedMapTest
{
    @Test
    public void joinsMaps() {
        final Map.Entry<String, String> first = new MapEntry<>("first", "value1");
        final Map.Entry<String, String> second = new MapEntry<>("second", "value2");
        MatcherAssert.assertThat(
            new JoinedMap<>(
                new MapOf<>(first),
                new MapOf<>(second)
            ).entrySet(),
            Matchers.everyItem(
                Matchers.isIn(
                    new MapOf<>(first, second).entrySet()
                )
            )
        );
    }

    @Test
    public void overridesKeyOnJoin() {
        String key = "first";
        String value = "value3";
        MatcherAssert.assertThat(
            new JoinedMap<>(
                new MapOf<>(new MapEntry<>(key, "value1")),
                new MapOf<>(new MapEntry<>(key, "value2")),
                new MapOf<>(new MapEntry<>(key, value))
            ).get(key),
            new IsEqual<>(value)
        );
    }
}
