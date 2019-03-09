package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;
import hr.com.vgv.verano.http.parts.QueryParams;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public final class QueryParamsOfTest
{
    @Test
    public void buildQueryParamsInput(){
        MatcherAssert.assertThat(
            new QueryParams.Of(
                new HashDict(
                    new KvpOf("q.name", "John"),
                    new KvpOf("q.surname", "Smith")
                )
            ).asString(),
            new IsEqual<>("?name=John&surname=Smith")
        );
    }
}
