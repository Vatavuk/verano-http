package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;

import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

public class GetReq extends Dict.Template
{
    public GetReq(String path, Kvp... kvps)
    {
        this(path, new IterableOf<>(kvps));
    }

    public GetReq(String path, Iterable<Kvp> kvps) {
        super(() -> new HashDict(new Joined<>(new Path(path), kvps)));
    }
}
