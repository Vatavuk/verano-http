package hr.com.vgv.verano.http.request;

import org.cactoos.iterable.IterableOf;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;

public class Delete extends Request
{
    public Delete(String path) {
        this(path, new HashDict());
    }

    public Delete(String path, Kvp... kvps) {
        this(path, new IterableOf<>(kvps));
    }

    public Delete(String path, Iterable<Kvp> kvps) {
        this(path, new HashDict(kvps));
    }

    public Delete(Kvp... kvps) {
        this(new IterableOf<>(kvps));
    }

    public Delete(Iterable<Kvp> kvps) {
        this(new HashDict(kvps));
    }

    public Delete(Dict dict) {
        this("", dict);
    }

    public Delete(String path, Dict dict)
    {
        super(path, "DELETE", dict);
    }
}
