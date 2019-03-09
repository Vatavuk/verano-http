package hr.com.vgv.verano.http.parts.headers;

import hr.com.vgv.verano.http.parts.Header;

public class SetCookie extends Header
{
    public SetCookie(final String value)
    {
        super("Set-Cookie", value);
    }
}
