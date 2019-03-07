package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public final class FormParamsOfTest
{
    @Test
    public void buildsFormParamsInput() {
        MatcherAssert.assertThat(
            new FormParams.Of(
                new HashDict(
                    new KvpOf("f.name", "John"),
                    new KvpOf("f.surname", "Smith")
                )
            ).asString(),
            new IsEqual<>("name=John&surname=Smith")
        );
    }
}
