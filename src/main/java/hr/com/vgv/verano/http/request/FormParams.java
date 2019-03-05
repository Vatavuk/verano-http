package hr.com.vgv.verano.http.request;

import java.net.URLEncoder;

import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.JoinedText;
import org.cactoos.text.UncheckedText;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class FormParams extends DictInput.Simple
{
    private static final String KEY = "fparams";

    public FormParams(final Kvp... kvps)
    {
        super(
            new KvpOf(
                FormParams.KEY,
                new UncheckedText(
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
                            kvps
                        )
                    )
                ).asString()
            )
        );
    }

    static class Of implements Text {

        private final Dict dict;

        public Of(Dict dict)
        {
            this.dict = dict;
        }

        @Override
        public String asString()
        {
            return this.dict.get(FormParams.KEY, "");
        }
    }
}
