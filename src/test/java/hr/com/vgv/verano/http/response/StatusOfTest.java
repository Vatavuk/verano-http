package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link Status.Of}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class StatusOfTest {

    @Test
    public void extractsReasonPhraseFromDict() {
        MatcherAssert.assertThat(
            new Status.Of(
                new HashDict(
                    new KvpOf("status", "200"), new KvpOf("unknown", "")
                )
            ).intValue(),
            new IsEqual<>(200)
        );
    }
}
