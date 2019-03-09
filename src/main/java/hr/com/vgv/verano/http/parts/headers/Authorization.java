package hr.com.vgv.verano.http.parts.headers;

import hr.com.vgv.verano.http.parts.Header;

public class Authorization extends Header
{
    public Authorization(final String value)
    {
        super("Authorization", value);
    }
}
