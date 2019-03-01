package hr.com.vgv.verano.http.request;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;

public final class BodyOfTest
{
    @Test
    public void extractsBodyFromDict() {
        MatcherAssert.assertThat(
            new Body.Of(
                new HashDict(
                    new KvpOf("body", "test"), new KvpOf("unknown", "")
                )
            ).value(),
            new IsEqual<>("test")
        );
    }

    @Test
    public void extractsBodyFromDictWithFormParams() {
        MatcherAssert.assertThat(
            new Body.Of(
                new HashDict(
                    new KvpOf("f.param1", "test1"),
                    new KvpOf("f.param2", "test2")
                )
            ).value(),
            new IsEqual<>("param1=test1&param2=test2")
        );
    }
}
