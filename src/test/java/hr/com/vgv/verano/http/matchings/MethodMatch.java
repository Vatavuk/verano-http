package hr.com.vgv.verano.http.matchings;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.parts.Method;
import org.hamcrest.BaseMatcher;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class MethodMatch extends HamcrestMatching {

    public MethodMatch(final BaseMatcher<String> matcher) {
        super((Dict req) -> new Method.Of(req).asString(), matcher);
    }
}
