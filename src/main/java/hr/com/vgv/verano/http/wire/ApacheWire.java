package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.RequestUri;
import hr.com.vgv.verano.http.response.ReasonPhrase;
import hr.com.vgv.verano.http.response.Status;

import hr.com.vgv.verano.http.wire.apache.ApacheContext;
import hr.com.vgv.verano.http.wire.apache.ApacheHeaders;
import hr.com.vgv.verano.http.wire.apache.ApacheRequest;
import java.io.IOException;
import java.net.URI;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.cactoos.iterable.IterableOf;

public class ApacheWire implements Wire
{
    private final Iterable<ApacheContext> contexts;

    private final Dict parameters;

    public ApacheWire(String uri)
    {
        this(uri, new IterableOf<>());
    }

    public ApacheWire(String uri, DictInput... inputs)
    {
        this(uri, new IterableOf<>(), new DictOf(inputs));
    }

    public ApacheWire(String uri, ApacheContext... contexts)
    {
        this(uri, new IterableOf<>(contexts));
    }

    public ApacheWire(String uri, Iterable<ApacheContext> contexts)
    {
        this(contexts, new DictOf(new RequestUri(uri)));
    }

    public ApacheWire(String uri, Iterable<ApacheContext> contexts,
        Dict parameters)
    {
        this(
            contexts,
            new HashDict(new JoinedDict(new RequestUri(uri), parameters))
        );
    }

    public ApacheWire(Iterable<ApacheContext> contexts, Dict parameters)
    {
        this.contexts = contexts;
        this.parameters = parameters;
    }

    @Override
    public final Dict send(Dict message) throws IOException
    {
        final Dict request = new JoinedDict(message, this.parameters);
        HttpClientBuilder builder = HttpClients.custom();
        final URI uri = new RequestUri.Of(request).uri();
        for (ApacheContext context : this.contexts)
        {
            builder = context.apply(uri, builder);
        }
        try(final CloseableHttpResponse response = builder.build()
            .execute(new ApacheRequest(request).value())) {
            final StatusLine status = response.getStatusLine();
            return new DictOf(
                new Status(status.getStatusCode()),
                new ReasonPhrase(status.getReasonPhrase()),
                new Body(EntityUtils.toString(response.getEntity())),
                new ApacheHeaders(response.getAllHeaders())
            );
        }
    }
}
