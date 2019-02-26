package hr.com.vgv.verano.http;

import java.util.Map;
import org.cactoos.map.MapEntry;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class KvpOf implements Kvp {

    private final Map.Entry<String, String> entry;

    public KvpOf(final String key, final String value) {
        this(new MapEntry<>(key, value));
    }

    public KvpOf(final Map.Entry<String, String> entry) {
        this.entry = entry;
    }

    @Override
    public String key() {
        return this.entry.getKey();
    }

    @Override
    public String value() {
        return this.entry.getValue();
    }
}
