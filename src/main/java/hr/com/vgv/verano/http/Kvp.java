package hr.com.vgv.verano.http;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Kvp {

    String key();

    String value();

    class Template implements Kvp {

        private final Kvp kvp;

        public Template(final Kvp kvp) {
            this.kvp = kvp;
        }

        @Override
        public final String key() {
            return this.kvp.key();
        }

        @Override
        public final String value() {
            return this.kvp.value();
        }
    }
}
