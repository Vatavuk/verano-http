package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.DictInput;
import org.cactoos.iterable.IterableOf;

public class Put extends Request
{
    public Put(DictInput... inputs) {
        this("", new IterableOf<>(inputs));
    }

    public Put(Iterable<DictInput> inputs) {
        this("", inputs);
    }

    public Put(String path) {
        this(path, new IterableOf<>());
    }

    public Put(String path, DictInput... inputs) {
        this(path, new IterableOf<>(inputs));
    }

    public Put(String path, Iterable<DictInput> inputs)
    {
        super(path, "PUT", inputs);
    }
}
