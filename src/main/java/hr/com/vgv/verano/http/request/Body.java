package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.KvpOf;
import hr.com.vgv.verano.http.DictInput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.cactoos.Text;

public class Body extends DictInput.Simple
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
            String body = new FormParams.Of(this.dict).asString();
            if (body.isEmpty()) {
                body = this.dict.get(KEY, "");
            }
            return body;
        }

        public final InputStream stream() {
            return new ByteArrayInputStream(this.asString().getBytes());
        }
    }
}
