package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class ReasonPhrase extends Kvp.Template {

    private static final String KEY = "reason";

    public ReasonPhrase(String reason)
    {
        super(new KvpOf(KEY, reason));
    }
}
