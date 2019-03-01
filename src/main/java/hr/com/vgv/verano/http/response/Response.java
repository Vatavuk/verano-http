package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.wire.AssertionWire;

public class Response extends Dict.Template
{
    public Response(Wire wire, Assertion assertion) {
        this(wire, new HashDict(), assertion);
    }

    public Response(Wire wire, Dict request) {
        this(wire, request, in -> {});
    }

    public Response(Wire wire, Dict request, Assertion assertion)
    {
        super(() -> new AssertionWire(wire, assertion).send(request));
    }

    public final void touch() {
        this.iterator();
    }
}
