package hr.com.vgv.verano.http.parts.headers;

import hr.com.vgv.verano.http.parts.Header;

public class Cookie extends Header
{
    public Cookie(final String value)
    {
        super("Cookie", value);
    }
}
