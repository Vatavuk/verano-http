package hr.com.vgv.verano.http.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.cactoos.map.MapEntry;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.UncheckedScalar;

import hr.com.vgv.verano.http.MapEntryEnvelope;

public class Body extends MapEntryEnvelope<String, String>
{
    private static final String KEY = "body";

    public Body(byte[] body) {
        this(new String(body, StandardCharsets.UTF_8));
    }

    public Body(String body)
    {
        super(new MapEntry<>(KEY, body));
    }

    public Body(Map<String, String> map)
    {
        this(Body.body(map));
    }

    public final InputStream stream() {
        return new ByteArrayInputStream(this.getValue().getBytes());
    }

    private static String body(Map<String, String> map)
    {
        return new UncheckedScalar<>(
            new Ternary<>(
                () -> map.containsKey(Body.KEY),
                () -> map.get(Body.KEY),
                () -> ""
            )
        ).value();
    }
}
