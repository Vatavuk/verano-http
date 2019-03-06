package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Cookie extends Kvp.Simple
{
    public Cookie(final String value)
    {
        super(new KvpOf("Cookie", value));
    }
}
