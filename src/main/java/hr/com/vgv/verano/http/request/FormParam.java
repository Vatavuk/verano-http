package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class FormParam extends Kvp.Template
{
    public FormParam(String key, String value)
    {
        super(new KvpOf(formParamKey(key), value));
    }

    public static class Of extends Kvp.Template {

        public Of(final String key, final Dict dict)
        {
            super(new KvpOf(key, dict.get(formParamKey(key))));
        }
    }

    private static String formParamKey(String key)
    {
        return String.format("f.%s", key);
    }
}
