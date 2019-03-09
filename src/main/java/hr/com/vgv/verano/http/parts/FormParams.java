package hr.com.vgv.verano.http.parts;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.KvpOf;
import java.net.URLEncoder;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.JoinedText;
import org.cactoos.text.UncheckedText;

public class FormParams extends DictInput.Simple
{
    public FormParams(final FormParam... params)
    {
        this(new IterableOf<>(params));
    }

    public FormParams(Iterable<FormParam> params) {
        super(
            (Dict dict) -> new DictOf(
                new Mapped<>(
                    input -> (DictInput) input,
                    params
                )
            )
        );
    }

    public static class Of implements Text {

        private final Dict dict;

        public Of(Dict dict)
        {
            this.dict = dict;
        }

        @Override
        public String asString()
        {
            return new UncheckedText(
                new JoinedText(
                    "&",
                    new Mapped<>(
                        input -> String.format(
                            "%s=%s", input.key(),
                            URLEncoder.encode(
                                input.value(),
                                "UTF-8"
                            )
                        ),
                        new Mapped<>(
                            in -> new KvpOf(in.key().substring(2), in.value()),
                            new Filtered<>(
                                input -> input.key().startsWith("f."),
                                this.dict
                            )
                        )
                    )
                )
            ).asString();
        }
    }
}
