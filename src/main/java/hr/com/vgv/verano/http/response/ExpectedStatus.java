package hr.com.vgv.verano.http.response;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.cactoos.collection.CollectionOf;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;

public class ExpectedStatus implements Assertion
{
    private final String message;

    private final Iterable<Integer> statuses;

    public ExpectedStatus(int status, String message) {
        this(message, new IterableOf<>(status));
    }

    public ExpectedStatus(String message, Integer status, Integer... additional) {
        this(message, new Joined<>(status, new IterableOf<>(additional)));
    }

    public ExpectedStatus(Integer status, Integer... additional) {
        this("", new Joined<>(status, new IterableOf<>(additional)));
    }

    public ExpectedStatus(String message, Iterable<Integer> statuses)
    {
        this.message = message;
        this.statuses = statuses;
    }

    @Override
    public final void test(Dict dict)
    {
        final int status = new Status.Of(dict).intValue();
        if (!new CollectionOf<>(this.statuses).contains(status)) {
            final String msg = String.format(
                "%s\n%s",
                this.message,
                String.format(
                    "Received response with status %d, instead of %d.\nReason: %s",
                    status,
                    this.statuses.iterator().next(),
                    new ReasonPhrase.Of(dict).value()
                )
            );
            throw new UncheckedIOException(new IOException(msg));
        }
    }
}
