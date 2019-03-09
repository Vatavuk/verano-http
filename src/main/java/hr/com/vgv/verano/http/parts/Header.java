package hr.com.vgv.verano.http.parts;

import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.KvpOf;

/**
 * Http header.
 * @since 1.0
 */
public class Header extends DictInput.Simple {

    public Header(String key, String value) {
        super(new KvpOf(String.format("h.%s", key), value));
    }
}
