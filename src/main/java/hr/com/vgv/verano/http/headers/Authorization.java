package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Authorization extends Kvp.Simple
{
    public Authorization(final String value)
    {
        super(new KvpOf("Authorization", value));
    }
}
