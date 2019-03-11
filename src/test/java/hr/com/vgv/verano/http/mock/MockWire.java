package hr.com.vgv.verano.http.mock;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Wire;
import org.cactoos.iterable.IterableOf;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class MockWire implements Wire {

    private final Iterable<Dict> responses;

    public MockWire(final Dict... responses) {
        this(new IterableOf<>(responses));
    }

    public MockWire(final Iterable<Dict> responses) {
        this.responses = responses;
    }

    @Override
    public final Dict send(final Dict request) {
        throw new UnsupportedOperationException("#send()");
    }
}
