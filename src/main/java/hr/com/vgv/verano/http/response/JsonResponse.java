package hr.com.vgv.verano.http.response;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.request.Body;

public class JsonResponse extends Response
{
    public JsonResponse(Wire wire, Assertion assertion)
    {
        this(wire, new HashDict(), assertion);
    }

    public JsonResponse(Wire wire, Dict request)
    {
        this(wire, request, in -> {
        });
    }

    public JsonResponse(Wire wire, Dict request, Assertion assertion)
    {
        super(wire, request, assertion);
    }

    public final JsonObject json()
    {
        try(JsonReader reader = this.reader()) {
            return reader.readObject();
        }
    }

    public final JsonArray jsonArray()
    {
        try(JsonReader reader = this.reader()) {
            return reader.readArray();
        }
    }

    private JsonReader reader()
    {
        return Json.createReader(
            new StringReader(new Body.Of(this).asString())
        );
    }
}
