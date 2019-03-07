package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.request.Header;

public class ContentType extends Header
{
    public ContentType(final String value)
    {
        super("Content-Type", value);
    }
}
