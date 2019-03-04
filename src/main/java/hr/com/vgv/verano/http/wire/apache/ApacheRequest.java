package hr.com.vgv.verano.http.wire.apache;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.cactoos.Scalar;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.request.Body;
import hr.com.vgv.verano.http.request.HeadersOf;
import hr.com.vgv.verano.http.request.Method;
import hr.com.vgv.verano.http.request.RequestUri;

public class ApacheRequest implements Scalar<HttpEntityEnclosingRequestBase>
{
    private final Dict request;

    public ApacheRequest(Dict request)
    {
        this.request = request;
    }

    @Override
    public final HttpEntityEnclosingRequestBase value() throws IOException
    {
        final HttpEntityEnclosingRequestBase result =
            new HttpEntityEnclosingRequestBase()
            {
                @Override
                public String getMethod()
                {
                    return new Method.Of(request).asString();
                }
            };
        final URI uri = new RequestUri.Of(this.request).uri();
        result.setConfig(
            RequestConfig.custom()
                .setCircularRedirectsAllowed(false)
                .setRedirectsEnabled(false)
                .build()
        );
        result.setURI(uri);
        result.setEntity(
            new BufferedHttpEntity(
                new InputStreamEntity(new Body.Of(this.request).stream())
            )
        );
        for (final Kvp header : new HeadersOf(this.request))
        {
            result.addHeader(header.key(), header.value());
        }
        return result;
    }
}
