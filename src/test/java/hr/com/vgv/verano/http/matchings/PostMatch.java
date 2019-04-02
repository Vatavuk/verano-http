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
package hr.com.vgv.verano.http.matchings;

import hr.com.vgv.verano.http.Dict;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.hamcrest.core.IsEqual;

/**
 * Post request matching.
 * @since 1.0
 */
public class PostMatch implements Matching {

    /**
     * Matchings.
     */
    private final Iterable<Matching> matchings;

    /**
     * Ctor.
     * @param matchings Matchings
     */
    public PostMatch(final Matching... matchings) {
        this(new IterableOf<>(matchings));
    }

    /**
     * Ctor.
     * @param matchings Matchings
     */
    public PostMatch(final Iterable<Matching> matchings) {
        this.matchings = new Joined<>(
            new MethodMatch(new IsEqual<>("POST")), matchings
        );
    }

    @Override
    public final Collection<String> match(final Dict request) {
        final List<String> result = new ArrayList<>(0);
        for (final Matching matching: this.matchings) {
            result.addAll(matching.match(request));
        }
        return result;
    }
}
