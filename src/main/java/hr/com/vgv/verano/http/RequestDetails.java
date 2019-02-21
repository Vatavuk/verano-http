package hr.com.vgv.verano.http;

import java.net.URI;

public interface RequestDetails
{
    String method();

    URI uri();

    Headers headers();

    Body body();
}
