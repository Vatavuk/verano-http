package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link ReasonPhrase.Of}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class ReasonPhraseOfTest {

    @Test
    public void extractsReasonPhraseFromDict() {
        MatcherAssert.assertThat(
            new ReasonPhrase.Of(
                new HashDict(
                    new KvpOf("reason", "test"), new KvpOf("unknown", "")
                )
            ).asString(),
            new IsEqual<>("test")
        );
    }
}
