package hr.com.vgv.verano.http.wire.apache;

import org.apache.http.Header;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;

import hr.com.vgv.verano.http.Kvp;

public class ApacheHeaders extends IterableEnvelope<Kvp>
{
    public ApacheHeaders(Header[] headers)
    {
        super(() -> new Mapped<>(
            header -> new hr.com.vgv.verano.http.request.Header(
                header.getName(), header.getValue()
            ),
            headers
        ));
    }
}
