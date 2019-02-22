package hr.com.vgv.verano.http.request;

import java.util.Map;

import org.cactoos.map.MapEntry;

import hr.com.vgv.verano.http.MapEntryEnvelope;

public class Header extends MapEntryEnvelope<String, String>
{
    public Header(final String key, final String value)
    {
        this(new MapEntry<>(key, value));
    }

    public Header(Map.Entry<String, String> entry)
    {
        super(
            new MapEntry<>(
                String.format("h.%s", entry.getKey()),
                entry.getValue()
            )
        );
    }
}
