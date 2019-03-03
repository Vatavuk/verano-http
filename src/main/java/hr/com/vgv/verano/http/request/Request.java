package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;

public class Request extends Dict.Template
{
    public Request(String method, Dict dict) {
        this("", method, dict);
    }

    public Request(String path, String method, Dict dict)
    {
        super(() -> new HashDict(
            /*new Joined<>(
                new IterableOf<>(
                    new Path(path), new Method(method)
                ),
                dict
            )*/
        ));
    }
}
