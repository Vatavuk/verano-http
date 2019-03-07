package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;
import javax.ws.rs.core.UriBuilder;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;

public class QueryParams extends DictInput.Simple
{
    public QueryParams(final QueryParam... params)
    {
        this(new IterableOf<>(params));
    }

    public QueryParams(Iterable<QueryParam> params) {
        super(
            (Dict dict) -> new DictOf(
                new Mapped<>(
                    input -> (DictInput) input,
                    params
                )
            )
        );
    }

    public static class Of implements Text {

        private final Dict dict;

        public Of(Dict dict)
        {
            this.dict = dict;
        }

        @Override
        public String asString()
        {
            return queryString(new Mapped<>(
                in -> new KvpOf(in.key().substring(2), in.value()),
                new Filtered<>(
                    input -> input.key().startsWith("q."),
                    this.dict
                )
            ));
        }
    }

    private static String queryString(Iterable<Kvp> kvps) {
        UriBuilder builder = UriBuilder.fromUri("");
        for (final Kvp kvp: kvps) {
            builder = builder.queryParam(kvp.key(), kvp.value());
        }
        return builder.build().toString();
    }
}
