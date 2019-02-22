package hr.com.vgv.verano.http.wire;

import org.apache.http.impl.client.HttpClientBuilder;

public interface ApacheContext
{
    HttpClientBuilder apply(HttpClientBuilder builder);
}
