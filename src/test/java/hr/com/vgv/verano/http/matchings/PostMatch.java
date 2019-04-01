package hr.com.vgv.verano.http.matchings;

import hr.com.vgv.verano.http.Dict;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.hamcrest.core.IsEqual;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class PostMatch implements Matching {

    private final Iterable<Matching> matchings;

    public PostMatch(final Matching... matchings) {
        this(new IterableOf<>(matchings));
    }

    public PostMatch(final Iterable<Matching> matchings) {
        this.matchings = new Joined<>(
            new MethodMatch(new IsEqual<>("POST")), matchings
        );
    }

    @Override
    public Collection<String> match(final Dict request) {
        final List<String> result = new ArrayList<>();
        for (final Matching matching: this.matchings) {
            result.addAll(matching.match(request));
        }
        return result;
    }
}
