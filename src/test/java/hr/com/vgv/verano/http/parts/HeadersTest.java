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
package hr.com.vgv.verano.http.parts;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.Kvp;
import java.util.List;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link Headers}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class HeadersTest {
    @Test
    public void buildHeaderInput() {
        final List<Kvp> kvps = new ListOf<>(
            new Headers(
                new Header("Content-Type", "application/json"),
                new Header("Authorization", "Bearer 1234")
            ).apply(new HashDict())
        );
        final Kvp content = kvps.get(0);
        MatcherAssert.assertThat(
            content.key(),
            new IsEqual<>("h.Content-Type")
        );
        MatcherAssert.assertThat(
            content.value(),
            new IsEqual<>("application/json")
        );
        final Kvp auth = kvps.get(1);
        MatcherAssert.assertThat(
            auth.key(),
            new IsEqual<>("h.Authorization")
        );
        MatcherAssert.assertThat(
            auth.value(),
            new IsEqual<>("Bearer 1234")
        );
    }
}
