package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class ContentType extends Kvp.Simple
{
    public ContentType(final String value)
    {
        super(new KvpOf("Content-Type", value));
    }
}
