package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.KvpOf;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.cactoos.Text;
import org.cactoos.collection.CollectionOf;

public class Body extends RequestInput
{
    private static final String KEY = "body";

    public Body(byte[] body) {
        this(new String(body, StandardCharsets.UTF_8));
    }

    public Body(String body)
    {
        super(new KvpOf(KEY, body));
    }

    public static class Of implements Text {

        private final Dict dict;

        public Of(final Dict dict) {
            this.dict = dict;
        }

        @Override
        public String asString() {
            final FormParamsOf params = new FormParamsOf(this.dict);
            final String body;
            if (new CollectionOf<>(params).isEmpty()) {
                body = this.dict.get(KEY, "");
            } else {
                body = params.toString();
            }
            return body;
        }

        public final InputStream stream() {
            return new ByteArrayInputStream(this.asString().getBytes());
        }
    }
}
