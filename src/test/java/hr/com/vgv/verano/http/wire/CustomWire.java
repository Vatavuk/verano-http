package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.headers.Authorization;
import hr.com.vgv.verano.http.headers.Cookie;
import java.io.IOException;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class CustomWire implements Wire {

    private final Wire origin;

    public CustomWire(final Wire origin) {
        this.origin = origin;
    }

    @Override
    public Dict send(final Dict request) throws IOException {
        final String token = login();
        return this.origin.send(
            new JoinedDict(
                request,
                new DictOf(
                    new Authorization(token),
                    new Cookie("someCookie")
                )
            )
        );
    }
}
