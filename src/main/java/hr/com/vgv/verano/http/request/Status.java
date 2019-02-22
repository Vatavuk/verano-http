package hr.com.vgv.verano.http.request;

import java.util.Map;

import org.cactoos.map.MapEntry;

import hr.com.vgv.verano.http.MapEntryEnvelope;

public class Status extends MapEntryEnvelope<String, String>
{
    private static final String KEY = "status";

    public Status(Integer status) {
        this(status.toString());
    }

    public Status(String status)
    {
        super(new MapEntry<>(Status.KEY, status));
    }

    public Status(Map<String, String> map) {
        super(Status.KEY, map);
    }

    public final int intValue() {
        return Integer.parseInt(this.getValue());
    }
}
