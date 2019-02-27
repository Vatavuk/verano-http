package hr.com.vgv.verano.http.wire;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.cactoos.Scalar;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.request.Body;
import hr.com.vgv.verano.http.request.Method;
import hr.com.vgv.verano.http.request.Path;
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
        final HttpEntityEnclosingRequestBase req =
            new HttpEntityEnclosingRequestBase()
            {
                @Override
                public String getMethod()
                {
                    return new Method.Of(request).value();
                }
            };
        final URI uri = URI.create(
            String.format(
                "%s%s",
                new RequestUri.Of(request).value(),
                new Path.Of(request).value()
            )
        );
        req.setConfig(
            RequestConfig.custom()
                .setCircularRedirectsAllowed(false)
                .setRedirectsEnabled(false)
                //.setConnectTimeout(connect)
                //.setSocketTimeout(read)
                .build()
        );
        req.setURI(uri);
        req.setEntity(
            new BufferedHttpEntity(
                new InputStreamEntity(new Body.Of(request).stream()
                )
            )
        );
        /*for (final Map.Entry<String, String> header : headers)
        {
            req.addHeader(header.getKey(), header.getValue());
        }*/
        return req;
    }
}
