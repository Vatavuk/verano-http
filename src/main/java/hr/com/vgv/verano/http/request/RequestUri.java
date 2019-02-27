package hr.com.vgv.verano.http.request;

import java.net.URI;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class RequestUri extends Kvp.Template
{
    private static final String KEY = "uri";

    public RequestUri(final String uri)
    {
        super(new KvpOf(KEY, uri));
    }

    public static class Of extends Kvp.Template {

        public Of(final Dict dict)
        {
            super(
                new KvpOf(
                    KEY,
                    dict.get(KEY, "") + new Path.Of(dict).value()
                )
            );
        }

        public URI uri() {
            return URI.create(this.value());
        }
    }
}
