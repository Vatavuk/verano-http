package hr.com.vgv.verano.http.mock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.request.Request;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class MockWire implements Wire {

    private final Queue<Dict> responses;

    private final List<Dict> requests;

    /*public MockWire(final Dict... responses) {
        this(new IterableOf<>(responses));
    }*/

    public MockWire(final LinkedList<Dict> responses) {
        this.responses = responses;
        this.requests = new ArrayList<>();
    }

    @Override
    public final Dict send(final Dict request) {
        this.requests.add(request);
        return this.responses.poll();
    }

    public final void verifyRequests(Dict... requests) {

        new LevenshteinDistance().apply("", "")
    }
}
