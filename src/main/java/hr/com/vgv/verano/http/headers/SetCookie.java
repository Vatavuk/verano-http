package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class SetCookie extends Kvp.Simple
{
    public SetCookie(final String value)
    {
        super(new KvpOf("Set-Cookie", value));
    }
}
