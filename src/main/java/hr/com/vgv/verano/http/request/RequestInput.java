package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictAssembly;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.Kvp;
import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class RequestInput implements DictAssembly {

    private final UncheckedFunc<Dict, Dict> origin;

    public RequestInput(final Kvp... kvps) {
        this((Dict dict) -> new JoinedDict(
            dict,
            new HashDict(kvps))
        );
    }

    public RequestInput(final Func<Dict, Dict> origin) {
        this.origin = new UncheckedFunc<>(origin);
    }

    @Override
    public final Dict apply(final Dict dict) {
        return this.origin.apply(dict);
    }
}
