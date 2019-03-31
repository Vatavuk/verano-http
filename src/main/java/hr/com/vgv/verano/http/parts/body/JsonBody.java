/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hr.com.vgv.verano.http.parts.body;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.parts.Body;
import java.io.StringReader;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

/**
 * Http body as json.
 * @since 1.0
 */
public class JsonBody extends DictInput.Envelope {

    /**
     * Ctor.
     * @param json Json
     */
    public JsonBody(final JsonStructure json) {
        super(() -> {
            final StringWriter writer = new StringWriter();
            Json.createWriter(writer).write(json);
            return  new Body(writer.toString());
        });
    }

    /**
     * Json body from response.
     */
    public static class Of extends Body.Of {

        /**
         * Ctor.
         * @param response Response
         */
        public Of(final Dict response) {
            super(response);
        }

        /**
         * Body as json.
         * @return Json Json
         */
        public final JsonObject json() {
            try (JsonReader reader = this.reader()) {
                return reader.readObject();
            }
        }

        /**
         * Body as json array.
         * @return JsonArray Json array
         */
        public final JsonArray jsonArray() {
            try (JsonReader reader = this.reader()) {
                return reader.readArray();
            }
        }

        /**
         * Read json from string.
         * @return JsonReader Json reader
         */
        public final JsonReader reader() {
            return Json.createReader(
                new StringReader(this.asString())
            );
        }
    }
}
