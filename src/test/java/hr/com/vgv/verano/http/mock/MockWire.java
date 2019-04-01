package hr.com.vgv.verano.http.mock;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.matchings.Matching;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.cactoos.list.ListOf;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class MockWire implements Wire {

    private final List<Dict> received;

    public MockWire(final Dict... responses) {
        this(new ListOf<>(responses));
    }

    public MockWire(final List<Dict> responses) {
        this.received = new ArrayList<>();
    }

    @Override
    public final Dict send(final Dict request) {
        this.received.add(request);
        return request;
    }

    public final void assertThat(Matching matching) {
        final Collection<String> errors = this.closestMatch(matching);
        if (!errors.isEmpty()) {
            throw new AssertionError(errors.iterator().next());
        }
    }

    public final void assertThat(Dict... requests) {

        for (final Dict request : requests) {
            boolean matched = false;
            for (final Dict rec : this.received) {
                if (request.toString().equals(rec.toString())) {
                    matched = true;
                }
            }
            if (!matched) {
                throw new AssertionError("No request matching found");
            }
        }

    }

    private Dict closestRequest(final Dict dict) {
        Dict closest = dict;
        Integer distance = null;
        final String request = dict.toString();
        for (final Dict rec : this.received) {
            final int result = new LevenshteinDistance()
                .apply(request, rec.toString());
            if (distance == null) {
                distance = result;
                closest = rec;
            } else {
                if (result < distance) {
                    distance = result;
                    closest = rec;
                }
            }
        }
        return closest;
    }

    private Collection<String> closestMatch(final Matching matching) {
        Collection<String> result = null;
        for (final Dict request : this.received) {
            final Collection<String> errors = matching.match(request);
            if (result == null) {
                result = errors;
            } else {
                if (errors.isEmpty()) {
                    result = errors;
                } else {
                    if (errors.size() < result.size()) {
                        result = errors;
                    }
                }
            }
        }
        return result;
    }
}
