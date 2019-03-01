package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Status extends Kvp.Template
{
    private static final String KEY = "status";

    public Status(Integer status)
    {
        this(status.toString());
    }

    public Status(String status)
    {
        super(new KvpOf(Status.KEY, status));
    }

    public static class Of extends Kvp.Template
    {
        public Of(Dict dict)
        {
            super(new KvpOf(KEY, dict.get(KEY)));
        }

        public final int intValue()
        {
            return Integer.parseInt(this.value());
        }
    }
}
