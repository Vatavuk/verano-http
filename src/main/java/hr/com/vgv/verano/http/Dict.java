package hr.com.vgv.verano.http;

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
}
