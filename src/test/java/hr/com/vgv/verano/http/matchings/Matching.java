package hr.com.vgv.verano.http.matchings;

import hr.com.vgv.verano.http.Dict;
import java.util.Collection;

/**
 * Request data matching.
 * @since 1.0
 */
public interface Matching {

    /**
     * Match request data.
     * @param request Request
     * @return Errors Errors
     */
    Collection<String> match(Dict request);
}
