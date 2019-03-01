package hr.com.vgv.verano.http.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.cactoos.collection.CollectionOf;

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
            super(new KvpOf(KEY, buildBody(dict)));
        }

        public final InputStream stream() {
            return new ByteArrayInputStream(this.value().getBytes());
        }

        private static String buildBody(Dict dict) {
            final FormParamsOf params = new FormParamsOf(dict);
            final String body;
            if (new CollectionOf<>(params).isEmpty()) {
                body = dict.get(KEY, "");
            } else {
                body = params.toString();
            }
            return body;
        }
    }
}
