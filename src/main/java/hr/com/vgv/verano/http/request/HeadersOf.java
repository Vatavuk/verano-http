package hr.com.vgv.verano.http.request;

import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class HeadersOf extends IterableEnvelope<Kvp>
{
    public HeadersOf(Dict dict)
    {
        super(
            () -> new Mapped<>(
                in -> new KvpOf(in.key().substring(2), in.value()),
                new Filtered<>(
                    input -> input.key().startsWith("h."),
                    dict
                )
            )
        );
    }
}
