package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.request.Header;

public class Cookie extends Header
{
    public Cookie(final String value)
    {
        super("Cookie", value);
    }
}
