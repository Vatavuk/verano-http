package hr.com.vgv.verano.http;

import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.func.UncheckedFunc;

/**
 * Input to be attached to a dictionary.
 * @since 1.0
 */
public interface DictInput
{
    Dict apply(Dict dict);

    class Simple implements DictInput
    {
        private final UncheckedFunc<Dict, Dict> origin;

        public Simple(final Scalar<DictInput> input) {
            this((Dict dict) -> new JoinedDict(
                dict, new DictOf(input.value()))
            );
        }

        public Simple(final Kvp... kvps) {
            this((Dict dict) -> new JoinedDict(
                dict, new HashDict(kvps))
            );
        }

        public Simple(final Func<Dict, Dict> origin) {
            this.origin = new UncheckedFunc<>(origin);
        }

        @Override
        public final Dict apply(final Dict dict) {
            return this.origin.apply(dict);
        }
    }
}
