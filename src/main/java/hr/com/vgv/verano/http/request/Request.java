package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.parts.Method;
import hr.com.vgv.verano.http.parts.Path;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;

public class Request extends Dict.Simple
{
    public Request(String method, DictInput... inputs) {
        this("", method, inputs);
    }

    public Request(String path, String method, DictInput... inputs)
    {
        this(path, method, new IterableOf<>(inputs));
    }

    public Request(String path, String method, Iterable<DictInput> inputs)
    {
        super(
            () -> new DictOf(
                new Joined<DictInput>(
                    new IterableOf<>(
                        new Path(path), new Method(method)
                    ),
                    inputs
                )
            )
        );
    }
}
