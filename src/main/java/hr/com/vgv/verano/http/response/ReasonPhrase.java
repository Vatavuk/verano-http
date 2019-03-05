package hr.com.vgv.verano.http.response;

import org.cactoos.Text;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.KvpOf;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class ReasonPhrase extends DictInput.Simple {

    private static final String KEY = "reason";

    public ReasonPhrase(String reason)
    {
        super(new KvpOf(KEY, reason));
    }

    public static class Of implements Text
    {
        private final Dict dict;

        public Of(Dict dict)
        {
            this.dict = dict;
        }

        @Override
        public final String asString()
        {
            return dict.get(KEY, "");
        }
    }
}
