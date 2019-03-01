package hr.com.vgv.verano.http.request;

import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.JoinedText;
import org.cactoos.text.UncheckedText;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class FormParamsOf extends IterableEnvelope<Kvp>
{
    public FormParamsOf(Dict dict)
    {
        super(
            () -> new Mapped<>(
                in -> new KvpOf(in.key().substring(2), in.value()),
                new Filtered<>(
                    input -> input.key().startsWith("f."),
                    dict
                )
            )
        );
    }

    @Override
    public String toString()
    {
        return new UncheckedText(
            new JoinedText(
                "&",
                new Mapped<>(
                    input -> String.format(
                        "%s=%s", input.key(), input.value()
                    ),
                    this
                )
            )
        ).asString();
    }
}
