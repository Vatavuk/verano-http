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
package hr.com.vgv.verano.http.request;

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.parts.headers.ContentType;
import org.cactoos.collection.CollectionOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link Post}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class PostTest {

    @Test
    public void buildsPostRequest() {
        final Dict dict = new Post("/items", new ContentType("text/html"));
        MatcherAssert.assertThat(
            dict.get("method"),
            new IsEqual<>("POST")
        );
        MatcherAssert.assertThat(
            dict.get("path"),
            new IsEqual<>("/items")
        );
        MatcherAssert.assertThat(
            dict.get("h.Content-Type"),
            new IsEqual<>("text/html")
        );
    }

    @Test
    public void buildsPostRequestWithoutParameters() {
        final Dict dict = new Post("/items");
        MatcherAssert.assertThat(
            new CollectionOf<>(dict).size(),
            new IsEqual<>(2)
        );
        MatcherAssert.assertThat(
            dict.get("method"),
            new IsEqual<>("POST")
        );
        MatcherAssert.assertThat(
            dict.get("path"),
            new IsEqual<>("/items")
        );
    }

    @Test
    public void buildsEmptyPostRequest() {
        final Dict dict = new Post();
        MatcherAssert.assertThat(
            new CollectionOf<>(dict).size(),
            new IsEqual<>(2)
        );
        MatcherAssert.assertThat(
            dict.get("method"),
            new IsEqual<>("POST")
        );
        MatcherAssert.assertThat(
            dict.get("path").isEmpty(),
            new IsEqual<>(true)
        );
    }

    @Test
    public void buildsPostRequestWithEmptyPath() {
        final Dict dict = new Post(new ContentType("text/html"));
        MatcherAssert.assertThat(
            new CollectionOf<>(dict).size(),
            new IsEqual<>(3)
        );
        MatcherAssert.assertThat(
            dict.get("method"),
            new IsEqual<>("POST")
        );
        MatcherAssert.assertThat(
            dict.get("path").isEmpty(),
            new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            dict.get("h.Content-Type"),
            new IsEqual<>("text/html")
        );
    }
}
