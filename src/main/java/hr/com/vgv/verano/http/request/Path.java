package hr.com.vgv.verano.http.request;

import java.util.Map;

import org.cactoos.map.MapEntry;

import hr.com.vgv.verano.http.MapEntryEnvelope;

public class Path extends MapEntryEnvelope<String, String>
{
    private static final String KEY = "path";

    public Path(String path)
    {
        super(new MapEntry<>(Path.KEY, path));
    }

    public Path(Map<String, String> map) {
        super(Path.KEY, map);
    }
}
