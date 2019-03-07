package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.request.Header;

public class Accept extends Header
{
    public Accept(final String value)
    {
        super("Accept", value);
    }
}
