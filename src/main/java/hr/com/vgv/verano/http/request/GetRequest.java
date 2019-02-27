package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;

import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

public class GetRequest extends Dict.Template
{
    public GetRequest(String path, Kvp... kvps)
    {
        this(path, new IterableOf<>(kvps));
    }

    public GetRequest(String path, Iterable<Kvp> kvps)
    {
        super(
            () -> new HashDict(
                new Joined<>(
                    new IterableOf<>(
                        new Method("GET"), new Path(path)
                    ),
                    kvps
                )
            )
        );
    }
}
