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
package hr.com.vgv.verano.http.response;

import hr.com.vgv.verano.http.HashDict;
import hr.com.vgv.verano.http.KvpOf;
import java.io.UncheckedIOException;
import org.cactoos.text.TextOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test case for {@link ExpectedStatus}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class ExpectedStatusTest {

    /**
     * Junit exception rule.
     */
    @Rule
    public final ExpectedException error = ExpectedException.none();

    @Test
    public void statusMatches() {
        new ExpectedStatus(200, new TextOf("msg"))
            .verify(
                new HashDict(
                    new KvpOf("status", "200")
                )
            );
    }

    @Test
    public void statusMismatch() {
        this.error.expect(UncheckedIOException.class);
        this.error.expectMessage(
            String.format(
                "%s\nReceived response with status %d, instead of %d.\nReason: %s\nUrl: %s\nBody: %s",
                "msg",
                404,
                200,
                "some reason",
                "localhost",
                "body"
            )
        );
        new ExpectedStatus(200, new FailWith("msg"))
            .verify(
                new HashDict(
                    new KvpOf("status", "404"),
                    new KvpOf("reason", "some reason"),
                    new KvpOf("uri", "localhost"),
                    new KvpOf("body", "body")
                )
            );
    }

}
