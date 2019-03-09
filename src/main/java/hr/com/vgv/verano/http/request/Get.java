package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.DictInput;
import org.cactoos.iterable.IterableOf;

public class Get extends Request
{
    public Get(DictInput... inputs) {
        this("", new IterableOf<>(inputs));
    }

    public Get(Iterable<DictInput> inputs) {
        this("", inputs);
    }

    public Get(String path) {
        this(path, new IterableOf<>());
    }

    public Get(String path, DictInput... inputs) {
        this(path, new IterableOf<>(inputs));
    }

    public Get(String path, Iterable<DictInput> inputs)
    {
        super(path, "GET", inputs);
    }
}
