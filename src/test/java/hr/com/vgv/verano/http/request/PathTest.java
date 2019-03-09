package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;
import hr.com.vgv.verano.http.parts.Path;
import org.cactoos.collection.CollectionOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public final class PathTest {

    @Test
    public void concatenatesPath() {
        final Dict dict = new Path("/items")
            .apply(
                new HashDict(
                    new KvpOf("aaa", "test"),
                    new KvpOf("path", "localhost")
                )
            );
        MatcherAssert.assertThat(
            new CollectionOf<>(dict).size(),
            new IsEqual<>(3)
        );
        MatcherAssert.assertThat(
            dict.get("path"),
            new IsEqual<>("localhost/items")
        );
    }
}
