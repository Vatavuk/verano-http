package hr.com.vgv.verano.http.request;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;

import org.cactoos.Text;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;
import hr.com.vgv.verano.http.DictInput;

public class RequestUri extends DictInput.Simple
{
    private static final String KEY = "uri";

    public RequestUri(final String uri)
    {
        super(new KvpOf(KEY, uri));
    }

    public static class Of implements Text
    {
        private final Dict dict;

        public Of(Dict dict)
        {
            this.dict = dict;
        }

        @Override
        public final String asString()
        {
            final String base = dict.get(KEY, "") + new Path.Of(dict).asString();
            UriBuilder builder = UriBuilder.fromUri(base);
            for (final Kvp kvp: new QueryParamsOf(dict)) {
                builder = builder.queryParam(kvp.key(), kvp.value());
            }
            return builder.build().toString();
        }

        public final URI uri() {
            return URI.create(this.asString());
        }
    }
}
