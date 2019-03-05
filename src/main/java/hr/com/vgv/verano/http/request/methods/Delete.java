package hr.com.vgv.verano.http.request.methods;

import org.cactoos.iterable.IterableOf;

import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.request.Request;

public class Delete extends Request
{
    public Delete(DictInput... inputs) {
        this("", new IterableOf<>(inputs));
    }

    public Delete(Iterable<DictInput> inputs) {
        this("", inputs);
    }

    public Delete(String path) {
        this(path, new IterableOf<>());
    }

    public Delete(String path, DictInput... inputs) {
        this(path, new IterableOf<>(inputs));
    }

    public Delete(String path, Iterable<DictInput> inputs)
    {
        super(path, "DELETE", inputs);
    }
}
