package hr.com.vgv.verano.http.request;

import java.util.Map;

import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;

import hr.com.vgv.verano.http.Joined;
import hr.com.vgv.verano.http.Message;

public class GetReq extends MapEnvelope<String, String> implements Message
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
