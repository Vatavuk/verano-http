package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Path extends Kvp.Template
{
    private static final String KEY = "path";

    public Path(String path)
    {
        super(new KvpOf(KEY, path));
    }

    public static class Of extends Kvp.Template {

        public Of(final Dict dict)
        {
            super(new KvpOf(KEY, dict.get(KEY, "")));
        }
    }
}
