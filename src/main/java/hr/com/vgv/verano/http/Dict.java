package hr.com.vgv.verano.http;

import java.util.Iterator;

import org.cactoos.Scalar;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Dictionary.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Dict extends Iterable<Kvp> {

    String get(String key);

    String get(String key, String def);

    boolean contains(String key);

    class Template implements Dict {

        private final UncheckedScalar<Dict> origin;

        public Template(Scalar<Dict> origin)
        {
            this.origin = new UncheckedScalar<>(new StickyScalar<>(origin));
        }

        @Override
        public final String get(String key)
        {
            return this.origin.value().get(key);
        }

        @Override
        public final String get(String key, String def)
        {
            return this.origin.value().get(key, def);
        }

        @Override
        public final boolean contains(String key)
        {
            return this.origin.value().contains(key);
        }

        @Override
        public final Iterator<Kvp> iterator()
        {
            return this.origin.value().iterator();
        }
    }
}
