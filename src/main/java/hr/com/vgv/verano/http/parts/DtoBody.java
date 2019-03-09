package hr.com.vgv.verano.http.parts;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;

/**
 * Http body from dto object.
 * @since 1.0
 */
public class DtoBody extends DictInput.Simple {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        DtoBody.MAPPER.configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        );
    }

    public <T> DtoBody(T body) {
        super(() -> new Body(DtoBody.MAPPER.writeValueAsString(body)));
    }

    public static class Of extends Body.Of {

        public Of(final Dict dict) {
            super(dict);
        }

        public final <T> T as(Class<T> cls) throws Exception {
            return DtoBody.MAPPER.readValue(this.asString(), cls);
        }
    }
}
