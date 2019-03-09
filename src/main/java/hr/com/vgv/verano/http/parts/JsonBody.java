package hr.com.vgv.verano.http.parts;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Http body from json.
 * @since 1.0
 */
public class JsonBody extends DictInput.Simple {

    public JsonBody(final JsonObject json) {
        super(() -> new Body(json.toString()));
    }

    public static class Of extends Body.Of {

        public Of(final Dict dict) {
            super(dict);
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
                new StringReader(this.asString())
            );
        }
    }
}
