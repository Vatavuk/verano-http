package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Accept extends Kvp.Simple
{
    public Accept(final String value)
    {
        super(new KvpOf("Accept", value));
    }
}
