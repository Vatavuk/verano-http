package hr.com.vgv.verano.http.headers;

import java.util.Map;

import org.cactoos.map.MapEntry;

public class Header implements Map.Entry<String, String>
{
    private final Map.Entry<String, String> origin;

    public Header(final String key, final String value) {
        this(new MapEntry<>(key, value));
    }

    public Header(Map.Entry<String, String> origin)
    {
        this.origin = origin;
    }

    @Override
    public final String getKey()
    {
        return String.format("H.%s",this.origin.getKey());
    }

    @Override
    public final String getValue()
    {
        return this.origin.getValue();
    }

    @Override
    public final String setValue(String value)
    {
        throw new UnsupportedOperationException("#setValue");
    }
}
