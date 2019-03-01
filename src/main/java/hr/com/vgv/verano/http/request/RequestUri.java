package hr.com.vgv.verano.http.request;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;

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
                new KvpOf(KEY, buildUri(dict))
            );
        }

        public URI uri() {
            return URI.create(this.value());
        }

        private static String buildUri(Dict dict)
        {
            final String base = dict.get(KEY, "") + new Path.Of(dict).value();
            UriBuilder builder = UriBuilder.fromUri(base);
            for (final Kvp kvp: new QueryParamsOf(dict)) {
                builder = builder.queryParam(kvp.key(), kvp.value());
            }
            return builder.build().toString();
        }
    }
}
