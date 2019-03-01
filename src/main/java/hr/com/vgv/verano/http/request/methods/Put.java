package hr.com.vgv.verano.http.request.methods;

import org.cactoos.iterable.IterableOf;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.request.Request;

public class Put extends Request
{
    public Put(String path) {
        this(path, new HashDict());
    }

    public Put(String path, Kvp... kvps) {
        this(path, new IterableOf<>(kvps));
    }

    public Put(String path, Iterable<Kvp> kvps) {
        this(path, new HashDict(kvps));
    }

    public Put(Kvp... kvps) {
        this(new IterableOf<>(kvps));
    }

    public Put(Iterable<Kvp> kvps) {
        this(new HashDict(kvps));
    }

    public Put(Dict dict) {
        this("", dict);
    }

    public Put(String path, Dict dict)
    {
        super(path, "PUT", dict);
    }
}
