package hr.com.vgv.verano.http.request;


import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Method extends Kvp.Template
{
    private static final String KEY = "method";

    public Method(String method)
    {
        super(new KvpOf(KEY, method));
    }

    public static class Of extends Kvp.Template {

        public Of(final Dict dict)
        {
            super(new KvpOf(KEY, dict.get(KEY, "GET")));
        }
    }
}
