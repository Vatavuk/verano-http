package hr.com.vgv.verano.http.parts.headers;

import hr.com.vgv.verano.http.parts.Header;

public class ContentType extends Header
{
    public ContentType(final String value)
    {
        super("Content-Type", value);
    }
}
