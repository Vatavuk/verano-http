package hr.com.vgv.verano.http;

import org.cactoos.iterable.Joined;

public class JoinedDict extends Dict.Template
{
    public JoinedDict(Kvp kvp, Dict dict) {
        this(new HashDict(kvp), dict);
    }

    public JoinedDict(Dict... dicts)
    {
        super(() -> new HashDict(new Joined<>(dicts)));
    }
}
