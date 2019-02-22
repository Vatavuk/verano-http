package hr.com.vgv.verano.http.headers;

import java.util.Map;

import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;

public class Headers extends MapEnvelope<String, String>
{
    public Headers(Map.Entry<String, String>... headers)
    {
        super(() -> new MapOf<>(new Mapped<>(Header::new, headers)));
    }
}
