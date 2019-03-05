package hr.com.vgv.verano.http.request;

import javax.ws.rs.core.UriBuilder;

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class QueryParams extends DictInput.Simple
{
    private static final String KEY = "qparams";

    public QueryParams(final Kvp... kvps)
    {
        this(new IterableOf<>(kvps));
    }

    public QueryParams(Iterable<Kvp> kvps) {
        super(new KvpOf(KEY, queryString(kvps)));
    }

    private static final String queryString(Iterable<Kvp> kvps) {
        UriBuilder builder = UriBuilder.fromUri("");
        for (final Kvp kvp: kvps) {
            builder = builder.queryParam(kvp.key(), kvp.value());
        }
        return builder.build().toString();
    }

    static class Of implements Text {

        private final Dict dict;

        public Of(Dict dict)
        {
            this.dict = dict;
        }

        @Override
        public String asString()
        {
            return this.dict.get(KEY, "");
        }
    }
}
