package hr.com.vgv.verano.http.matchings;

import hr.com.vgv.verano.http.Dict;
import java.util.Collection;
import org.cactoos.Func;
import org.cactoos.collection.CollectionOf;
import org.cactoos.func.UncheckedFunc;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * Matching backed by Hamcrest.
 * @since 1.0
 */
public class HamcrestMatching implements Matching {

    private final UncheckedFunc<Dict, String> func;

    private final Matcher<String> matcher;

    public HamcrestMatching(final Func<Dict, String> func,
        final Matcher<String> matcher) {
        this.func = new UncheckedFunc<>(func);
        this.matcher = matcher;
    }

    @Override
    public Collection<String> match(final Dict request) {
        final Collection<String> result;
        final String matching = this.func.apply(request);
        if (this.matcher.matches(matching)) {
            result = new CollectionOf<>();
        } else {
            final StringDescription description = new StringDescription();
            description
                .appendText("\nExpected: ")
                .appendDescriptionOf(this.matcher)
                .appendText("\n     but: ");
            this.matcher.describeMismatch(matching, description);
            result = new CollectionOf<>(description.toString());
        }
        return result;
    }
}
