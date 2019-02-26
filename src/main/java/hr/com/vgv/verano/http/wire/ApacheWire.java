package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.Joined;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.request.Body;
import hr.com.vgv.verano.http.request.Method;
import hr.com.vgv.verano.http.request.Path;
import hr.com.vgv.verano.http.request.RequestUri;
import hr.com.vgv.verano.http.request.Status;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapOf;

public class ApacheWire implements Wire
{
    private final Iterable<ApacheContext> contexts;

    private final Map<String, String> message;

    public ApacheWire(String uri)
    {
        this(uri, new IterableOf<>());
    }

    public ApacheWire(String uri, ApacheContext... contexts)
    {
        this(uri, new IterableOf<>(contexts));
    }

    public ApacheWire(String uri, Iterable<ApacheContext> contexts)
    {
        this(contexts, new MapOf<>(new RequestUri(uri)));
    }

    public ApacheWire(String uri, Iterable<ApacheContext> contexts, Map<String, String> request)
    {
        this(contexts, new Joined<>(new RequestUri(uri), request));
    }

    public ApacheWire(Iterable<ApacheContext> contexts, Map<String, String> request)
    {
        this.contexts = contexts;
        this.message = request;
    }

    @Override
    public Map<String, String> send(Map<String, String> message) throws IOException
    {
        final Map<String, String> request = new Joined<>(
            this.message, message
        );
        HttpClientBuilder builder = HttpClients.custom();
        for (ApacheContext context : this.contexts)
        {
            builder = context.apply(builder);
        }
        final CloseableHttpResponse response =
            builder.build().execute(ApacheWire.httpRequest(request));
        try
        {
            StatusLine status = response.getStatusLine();
            return new MapOf<>(
                new Status(status.getStatusCode()),
                new Body(EntityUtils.toString(response.getEntity()))
            );
            /*return new DefaultResponse(
                req,
                response.getStatusLine().getStatusCode(),
                response.getStatusLine().getReasonPhrase(),
                this.headers(response.getAllHeaders()),
                this.consume(response.getEntity())
            );*/
        }
        finally
        {
            response.close();
        }
    }

    private static HttpEntityEnclosingRequestBase httpRequest(final Map<String, String> request) throws IOException
    {
        final HttpEntityEnclosingRequestBase req =
            new HttpEntityEnclosingRequestBase()
            {
                @Override
                public String getMethod()
                {
                    return new Method(request).getValue();
                }
            };
        final URI uri = URI.create(
            String.format(
                "%s%s",
                new RequestUri(request).getValue(),
                new Path(request).getValue()
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
            new BufferedHttpEntity(new InputStreamEntity(new Body(request).stream()))
        );
        /*for (final Map.Entry<String, String> header : headers)
        {
            req.addHeader(header.getKey(), header.getValue());
        }*/
        return req;
    }
}
