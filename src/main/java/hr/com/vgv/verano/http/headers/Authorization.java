package hr.com.vgv.verano.http.headers;

import hr.com.vgv.verano.http.request.Header;

public class Authorization extends Header
{
    public Authorization(final String value)
    {
        super("Authorization", value);
    }
}
