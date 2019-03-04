package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.KvpOf;

import org.cactoos.Text;

public class Path extends RequestInput
{
    private static final String KEY = "path";

    public Path(final String path)
    {
        super(
            (dict) -> new JoinedDict(
                dict,
                new HashDict(new KvpOf(KEY, dict.get(KEY, "") + path))
            )
        );
    }

    public static class Of implements Text
    {
        private final Dict dict;

        public Of(final Dict dict)
        {
            this.dict = dict;
        }

        @Override
        public String asString()
        {
            return this.dict.get(KEY, "");
        }
    }
}
