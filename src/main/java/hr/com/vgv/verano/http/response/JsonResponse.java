package hr.com.vgv.verano.http.response;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import hr.com.vgv.verano.http.Assertion;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.request.Body;
import hr.com.vgv.verano.http.wire.AssertionWire;

public class JsonResponse extends Dict.Template
{
    public JsonResponse(Wire wire, Dict request) {
        this(wire, request, in -> {});
    }

    public JsonResponse(Wire wire, Dict request, Assertion assertion)
    {
        super(() -> new AssertionWire(wire, assertion).send(request));
    }

    public final JsonObject json() {
        JsonReader jsonReader = Json.createReader(
            new StringReader(new Body.Of(this).value())
        );
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        return json;
    }
}
