package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.KvpOf;

/**
 * Http query parameter.
 * @since 1.0
 */
public class QueryParam extends DictInput.Simple {

    public QueryParam(final String key, final String value) {
        super(new KvpOf(String.format("q.%s", key), value));
    }
}
