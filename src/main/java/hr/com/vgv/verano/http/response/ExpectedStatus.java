package hr.com.vgv.verano.http.response;

import org.cactoos.iterable.IterableOf;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;

public class ExpectedStatus implements Assertion
{
    private final String message;

    private final Iterable<Integer> statuses;

    public ExpectedStatus(int status, String message) {
        this(message, new IterableOf<>(status));
    }

    public ExpectedStatus(String message, Integer... statuses) {
        this(message, new IterableOf<>(statuses));
    }

    public ExpectedStatus(Integer... statuses) {
        this("", new IterableOf<>(statuses));
    }

    public ExpectedStatus(String message, Iterable<Integer> statuses)
    {
        this.message = message;
        this.statuses = statuses;
    }

    @Override
    public final void test(Dict dict)
    {
    }
}
