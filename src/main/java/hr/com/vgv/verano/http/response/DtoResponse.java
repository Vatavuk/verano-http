package hr.com.vgv.verano.http.response;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.request.Body;

public class DtoResponse extends Response
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public DtoResponse(Wire wire, Assertion assertion) {
        this(wire, new HashDict(), assertion);
    }

    public DtoResponse(Wire wire, Dict request) {
        this(wire, request, in -> {});
    }

    public DtoResponse(Wire wire, Dict request, Assertion assertion)
    {
        super(wire, request, assertion);
    }

    public final <T> T as(Class<T> cls) throws Exception {
        return MAPPER.readValue(new Body.Of(this).value(), cls);
    }
}
