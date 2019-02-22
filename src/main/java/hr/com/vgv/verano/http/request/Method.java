package hr.com.vgv.verano.http.request;

import java.util.Map;

import org.cactoos.map.MapEntry;

import hr.com.vgv.verano.http.MapEntryEnvelope;

public class Method extends MapEntryEnvelope<String, String>
{
    private static final String KEY = "method";

    public Method(String method)
    {
        super(new MapEntry<>(KEY, method));
    }

    public Method(Map<String, String> map) {
        super(Method.KEY, map);
    }
}
