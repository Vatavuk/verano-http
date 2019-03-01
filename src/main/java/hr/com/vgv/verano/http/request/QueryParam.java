package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class QueryParam extends Kvp.Template
{
    public QueryParam(String key, String value)
    {
        super(new KvpOf(queryParamKey(key), value));
    }

    public static class Of extends Kvp.Template {

        public Of(final String key, final Dict dict)
        {
            super(new KvpOf(key, dict.get(queryParamKey(key))));
        }
    }

    private static String queryParamKey(String key)
    {
        return String.format("q.%s", key);
    }
}
