package hr.com.vgv.verano.http.parts;

import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.KvpOf;

/**
 * Http form parameter.
 * @version $Id$
 * @since 1.0
 */
public class FormParam extends DictInput.Simple {

    public FormParam(final String key, final String value) {
        super(new KvpOf(String.format("f.%s", key), value));
    }
}
