package hr.com.vgv.verano.http;

import org.cactoos.iterable.IterableOf;

public class DictOf extends Dict.Template
{
    public DictOf(final DictInput... inputs) {
        this(new IterableOf<>(inputs));
    }

    public DictOf(final Iterable<DictInput> inputs)
    {
        super(() -> {
            Dict dict = new HashDict();
            for (final DictInput input: inputs) {
                dict = input.apply(dict);
            }
            return dict;
        });
    }
}
