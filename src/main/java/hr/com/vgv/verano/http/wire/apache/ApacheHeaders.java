package hr.com.vgv.verano.http.wire.apache;

import org.apache.http.Header;
import org.cactoos.iterable.Mapped;

import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.KvpOf;
import hr.com.vgv.verano.http.request.Headers;

public class ApacheHeaders extends DictInput.Simple
{
    public ApacheHeaders(Header[] headers)
    {
        super(dict -> new JoinedDict(
            dict,
            new Headers(
                new Mapped<>(
                    header -> new KvpOf(header.getName(), header.getValue()),
                    headers
                )
            ).apply(dict))
        );
    }
}
