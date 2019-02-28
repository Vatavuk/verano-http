package hr.com.vgv.verano.http.request;

import org.cactoos.iterable.IterableOf;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;

public class Get extends Request
{
    public Get(String path) {
        this(path, new HashDict());
    }

    public Get(String path, Kvp... kvps) {
        this(path, new IterableOf<>(kvps));
    }

    public Get(String path, Iterable<Kvp> kvps) {
        this(path, new HashDict(kvps));
    }

    public Get(Kvp... kvps) {
        this(new IterableOf<>(kvps));
    }

    public Get(Iterable<Kvp> kvps) {
        this(new HashDict(kvps));
    }

    public Get(Dict dict) {
        this("", dict);
    }

    public Get(String path, Dict dict)
    {
        super(path, "GET", dict);
    }
}
