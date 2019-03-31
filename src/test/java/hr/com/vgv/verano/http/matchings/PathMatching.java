package hr.com.vgv.verano.http.matchings;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.parts.Path;
import org.hamcrest.BaseMatcher;

/**
 * Matching of path data.
 * @version $Id$
 * @since 1.0
 */
public class PathMatching extends HamcrestMatching {

    public PathMatching(final BaseMatcher<String> matcher) {
        super((Dict req) -> new Path.Of(req).asString(), matcher);
    }
}
