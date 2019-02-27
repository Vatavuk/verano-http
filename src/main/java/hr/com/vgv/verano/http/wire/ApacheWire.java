package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.request.Body;
import hr.com.vgv.verano.http.request.RequestUri;
import hr.com.vgv.verano.http.response.ReasonPhrase;
import hr.com.vgv.verano.http.response.Status;
import java.io.IOException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

public class ApacheWire implements Wire
{
    private final Iterable<ApacheContext> contexts;

    private final Dict parameters;

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
        this(contexts, new HashDict(new RequestUri(uri)));
    }

    public ApacheWire(String uri, Iterable<ApacheContext> contexts, Dict parameters)
    {
        this(contexts, new HashDict(new Joined<>(new RequestUri(uri), parameters)));
    }

    public ApacheWire(Iterable<ApacheContext> contexts, Dict parameters)
    {
        this.contexts = contexts;
        this.parameters = parameters;
    }

    @Override
    public final Dict send(Dict message) throws IOException
    {
        final JoinedDict request = new JoinedDict(message, this.parameters);
        HttpClientBuilder builder = HttpClients.custom();
        for (ApacheContext context : this.contexts)
        {
            builder = context.apply(new RequestUri.Of(request).uri(), builder);
        }
        final CloseableHttpResponse response =
            builder.build()
                .execute(
                    new ApacheRequest(
                        request
                    ).value()
                );
        try
        {
            StatusLine status = response.getStatusLine();
            return new HashDict(
                new Status(status.getStatusCode()),
                new ReasonPhrase(status.getReasonPhrase()),
                new Body(EntityUtils.toString(response.getEntity()))
            );
            /*return new DefaultResponse(
                req,
                this.headers(response.getAllHeaders()),
            );*/
        }
        finally
        {
            response.close();
        }
    }
}
