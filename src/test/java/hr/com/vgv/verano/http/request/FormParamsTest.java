package hr.com.vgv.verano.http.request;

import org.cactoos.collection.CollectionOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;

public final class FormParamsTest
{
    @Test
    public void buildsFormParamsInput() {
        final Dict dict = new FormParams(
            new KvpOf("name", "John"),
            new KvpOf("surname", "Smith")
        ).apply(new HashDict());
        MatcherAssert.assertThat(
            new CollectionOf<>(dict).size(),
            new IsEqual<>(1)
        );
        MatcherAssert.assertThat(
            dict.get("fparams"),
            new IsEqual<>("name=John&surname=Smith")
        );
    }
}
