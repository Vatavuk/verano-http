package hr.com.vgv.verano.http.request;

import java.util.Map;

import org.cactoos.map.MapEntry;

import hr.com.vgv.verano.http.MapEntryEnvelope;

public class RequestUri extends MapEntryEnvelope<String, String>
{
    private static final String KEY = "uri";

    public RequestUri(String uri)
    {
        super(new MapEntry<>(RequestUri.KEY, uri));
    }

    public RequestUri(Map<String, String> map) {
        super(RequestUri.KEY, map);
    }
}
