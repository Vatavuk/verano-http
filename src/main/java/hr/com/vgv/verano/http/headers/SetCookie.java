package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.request.Header;

public class SetCookie extends Header
{
    public SetCookie(final String value)
    {
        super("Set-Cookie", value);
    }
}
