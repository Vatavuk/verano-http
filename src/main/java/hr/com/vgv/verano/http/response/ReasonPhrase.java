package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.MapEntryEnvelope;
import org.cactoos.map.MapEntry;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class ReasonPhrase extends MapEntryEnvelope<String, String> {

    private static final String KEY = "reason";

    public ReasonPhrase(String reason)
    {
        super(new MapEntry<>(ReasonPhrase.KEY, reason));
    }
}
