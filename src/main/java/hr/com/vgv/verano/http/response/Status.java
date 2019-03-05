package hr.com.vgv.verano.http.response;

import org.cactoos.Text;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.KvpOf;

public class Status extends DictInput.Simple
{
    private static final String KEY = "status";

    public Status(Integer status)
    {
        this(status.toString());
    }

    public Status(String status)
    {
        super(new KvpOf(Status.KEY, status));
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
            return dict.get(KEY);
        }

        public final int intValue()
        {
            return Integer.parseInt(this.asString());
        }
    }
}
