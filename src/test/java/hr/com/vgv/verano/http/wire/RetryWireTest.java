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
package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.mock.MockAnswer;
import hr.com.vgv.verano.http.mock.MockWire;
import hr.com.vgv.verano.http.mock.PathMatch;
import hr.com.vgv.verano.http.request.Post;
import hr.com.vgv.verano.http.response.Response;
import hr.com.vgv.verano.http.response.Status;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test case for {@link RetryWire}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class RetryWireTest {

    /**
     * Junit exception rule.
     */
    @Rule
    public final ExpectedException error = ExpectedException.none();

    @Test
    public void retriesAndFails() throws Exception {
        this.error.expect(IllegalStateException.class);
        this.error.expectMessage("Failed after 3 attempts");
        new RetryWire(
            new MockWire(
                new MockAnswer(
                    new PathMatch(Matchers.containsString("/")),
                    new Response(new Status(500))
                )
            )
        ).send(new Post("/"));
    }

    @Test
    public void noRetries() throws Exception {
        MatcherAssert.assertThat(
            new RetryWire(
                new MockWire(
                    new MockAnswer(
                        new PathMatch(Matchers.containsString("/")),
                        new Response(new Status(200))
                    )
                )
            ).send(new Post("/")).get("status"),
            new IsEqual<>("200")
        );
    }

    @Test
    public void oneRetryAndFail() throws Exception {
        this.error.expect(IllegalStateException.class);
        this.error.expectMessage("Failed after 1 attempts");
        new RetryWire(
            input -> {
                throw new IllegalStateException("msg");
            },
            1
        ).send(new Post("/"));
    }
}
