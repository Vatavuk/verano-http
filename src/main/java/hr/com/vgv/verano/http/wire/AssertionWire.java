package hr.com.vgv.verano.http.wire;

import java.io.IOException;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Wire;

public class AssertionWire implements Wire
{
    private final Wire origin;

    private final Assertion assertion;

    public AssertionWire(Wire origin, Assertion assertion)
    {
        this.origin = origin;
        this.assertion = assertion;
    }

    @Override
    public final Dict send(Dict request) throws IOException
    {
        final Dict response = this.origin.send(request);
        this.assertion.test(response);
        return response;
    }
}
