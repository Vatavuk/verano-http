package hr.com.vgv.verano.http.response;

import java.io.StringReader;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.cactoos.map.MapEnvelope;
import org.cactoos.map.Sticky;

import hr.com.vgv.verano.http.Wire;
import hr.com.vgv.verano.http.request.Body;

public class JsonResponse extends MapEnvelope<String, String>
{
    public JsonResponse(Wire wire, Map<String, String> request)
    {
        super(() -> new Sticky<>(wire.send(request)));
    }

    public JsonObject json() {
        JsonReader jsonReader = Json.createReader(
            new StringReader(new Body(this).getValue())
        );
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        return json;
    }
}
