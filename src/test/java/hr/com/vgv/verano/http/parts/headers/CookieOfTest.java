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
package hr.com.vgv.verano.http.parts.headers;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link Cookie}.
 * @since 1.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class CookieOfTest {

    @Test
    public void extractsCookieFromDict() {
        MatcherAssert.assertThat(
            new Cookie.Of(
                "param1",
                new HashDict(
                    new KvpOf("h.Set-Cookie", "param1=val1; param2=val2"),
                    new KvpOf("unknown", "")
                )
            ).asString(),
            new IsEqual<>("val1")
        );
    }

    @Test(expected = IllegalStateException.class)
    public void cookieHeaderNotPresent() {
        new Cookie.Of(
            "param1", new HashDict(new KvpOf("unknown", ""))
        ).asString();
    }

    @Test(expected = IllegalStateException.class)
    public void cookieNotFound() {
        new Cookie.Of(
            "param1", new HashDict(new KvpOf("h.Set-Cookie", "param2=val2"))
        ).asString();
    }
}
