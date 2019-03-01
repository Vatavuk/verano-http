package hr.com.vgv.verano.http.request.methods;

import org.cactoos.iterable.IterableOf;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.request.Request;

public class Post extends Request
{
    public Post(String path) {
        this(path, new HashDict());
    }

    public Post(String path, Kvp... kvps) {
        this(path, new IterableOf<>(kvps));
    }

    public Post(String path, Iterable<Kvp> kvps) {
        this(path, new HashDict(kvps));
    }

    public Post(Kvp... kvps) {
        this(new IterableOf<>(kvps));
    }

    public Post(Iterable<Kvp> kvps) {
        this(new HashDict(kvps));
    }

    public Post(Dict dict) {
        this("", dict);
    }

    public Post(String path, Dict dict)
    {
        super(path, "POST", dict);
    }
}
