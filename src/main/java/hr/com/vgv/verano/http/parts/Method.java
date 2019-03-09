package hr.com.vgv.verano.http.parts;


import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.KvpOf;
import hr.com.vgv.verano.http.DictInput;

import org.cactoos.Text;

public class Method extends DictInput.Simple
{
    private static final String KEY = "method";

    public Method(String method)
    {
        super(new KvpOf(KEY, method));
    }

    public static class Of implements Text {

        private final Dict dict;

        public Of(final Dict dict) {
            this.dict = dict;
        }

        @Override
        public String asString() {
            return this.dict.get(KEY, "GET");
        }
    }
}
