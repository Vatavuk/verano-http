package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.DictInput;
import org.cactoos.iterable.IterableOf;

public class Post extends Request
{
    public Post(DictInput... inputs) {
        this("", new IterableOf<>(inputs));
    }

    public Post(Iterable<DictInput> inputs) {
        this("", inputs);
    }

    public Post(String path) {
        this(path, new IterableOf<>());
    }

    public Post(String path, DictInput... inputs) {
        this(path, new IterableOf<>(inputs));
    }

    public Post(String path, Iterable<DictInput> inputs)
    {
        super(path, "POST", inputs);
    }
}
