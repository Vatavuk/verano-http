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
package hr.com.vgv.verano.http.mock;

import hr.com.vgv.verano.http.Dict;
import java.util.Collection;
import org.cactoos.Func;
import org.cactoos.collection.CollectionOf;
import org.cactoos.func.UncheckedFunc;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * Matching backed by Hamcrest.
 * @since 1.0
 */
public class HamcrestMatching implements Matching {

    /**
     * Function that extracts parameter from request.
     */
    private final UncheckedFunc<Dict, String> func;

    /**
     * Matcher.
     */
    private final Matcher<String> matcher;

    /**
     * Ctor.
     * @param func Function that extracts parameter from request
     * @param matcher MAtcher
     */
    public HamcrestMatching(final Func<Dict, String> func,
        final Matcher<String> matcher) {
        this.func = new UncheckedFunc<>(func);
        this.matcher = matcher;
    }

    @Override
    public final Collection<String> match(final Dict request) {
        final Collection<String> result;
        final String matching = this.func.apply(request);
        if (this.matcher.matches(matching)) {
            result = new CollectionOf<>();
        } else {
            final StringDescription description = new StringDescription();
            description
                .appendText("\nExpected: ")
                .appendDescriptionOf(this.matcher)
                .appendText("\n     but: ");
            this.matcher.describeMismatch(matching, description);
            result = new CollectionOf<>(description.toString());
        }
        return result;
    }
}
