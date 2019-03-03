package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;
import org.cactoos.Text;
import org.cactoos.collection.Mapped;
import org.cactoos.text.SplitText;

public class Header extends Kvp.Template
{
    public Header(final String key, final String value)
    {
        super(new KvpOf(headerKey(key), value));
    }

    public static class Of extends Kvp.Template {

        public Of(final String key, final Dict dict)
        {
            super(new KvpOf(key, dict.get(headerKey(key))));
        }

        public Iterable<String> asList() {
            return new Mapped<>(
                Text::asString,
                new SplitText(this.value(), ",")
            );
        }
    }

    private static String headerKey(String key)
    {
        return String.format("h.%s", key);
    }
}
