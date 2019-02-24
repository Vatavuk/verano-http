package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Joined;
import java.util.Map;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;

public class GetReq extends MapEnvelope<String, String>
{
    @SafeVarargs
    public GetReq(String path, Map.Entry<String, String>... properties)
    {
        super(() -> new Joined<>(
            new MapOf<>(
                new Method("GET"),
                new Path(path)
            ),
            new MapOf<>(properties)
        ));
    }
}
