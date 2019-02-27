package hr.com.vgv.verano.http.wire;

import java.net.URI;

import org.apache.http.impl.client.HttpClientBuilder;

public interface ApacheContext
{
    HttpClientBuilder apply(URI uri, HttpClientBuilder builder);
}
