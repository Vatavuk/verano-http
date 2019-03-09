package hr.com.vgv.verano.http.parts;

import hr.com.vgv.verano.http.DictOf;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.Kvp;
import hr.com.vgv.verano.http.KvpOf;

public class Headers extends DictInput.Simple {

    public Headers(final Header... headers) {
        this(new IterableOf<>(headers));
    }

    public Headers(final Iterable<Header> headers) {
        super(
            (Dict dict) -> new DictOf(
                new Mapped<>(
                    input -> (DictInput) input,
                    headers
                )
            )
        );
    }

    public static class Of extends IterableEnvelope<Kvp> {

        public Of(final Dict dict) {
            super(() -> new Mapped<>(
                in -> new KvpOf(in.key().substring(2), in.value()),
                new Filtered<>(
                    input -> input.key().startsWith("h."),
                    dict
                )
            ));
        }

        public final Map<String, List<String>> asMap() {
            final ConcurrentMap<String, List<String>> result =
                new ConcurrentHashMap<>(0);
            for (final Kvp header : this) {
                result.putIfAbsent(header.key(), new LinkedList<>());
                result.get(header.key()).add(header.value());
            }
            return result;
        }
    }
}
