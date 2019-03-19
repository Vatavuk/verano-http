package hr.com.vgv.verano.http.mock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Wire;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.cactoos.scalar.MinOf;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class MockWire implements Wire {

    private final Queue<Dict> responses;

    private final List<Dict> received;

    /*public MockWire(final Dict... responses) {
        this(new IterableOf<>(responses));
    }*/

    public MockWire(final LinkedList<Dict> responses) {
        this.responses = responses;
        this.received = new ArrayList<>();
    }

    @Override
    public final Dict send(final Dict request) {
        this.received.add(request);
        return this.responses.poll();
    }

    public final void assertThat(Dict... requests) {

        for (final Dict request: requests) {
            boolean matched = false;
            for (final Dict rec: this.received) {
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
        for (final Dict rec: this.received) {
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
}
