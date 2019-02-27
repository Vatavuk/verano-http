package hr.com.vgv.verano.http.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Body extends Kvp.Template
{
    private static final String KEY = "body";

    public Body(byte[] body) {
        this(new String(body, StandardCharsets.UTF_8));
    }

    public Body(String body)
    {
        super(new KvpOf(KEY, body));
    }

    public static class Of extends Kvp.Template {

        public Of(final Dict dict)
        {
            super(new KvpOf(KEY, dict.get(KEY, "")));
        }

        public final InputStream stream() {
            return new ByteArrayInputStream(this.value().getBytes());
        }
    }
}
