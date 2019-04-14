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

import javax.xml.bind.DatatypeConverter;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Basic authorization header.
 * @since 1.0
 */
public class BasicAuth extends Authorization {

    /**
     * Ctor.
     * @param username Username
     * @param password Password
     */
    public BasicAuth(final String username, final String password) {
        super(BasicAuth.encode(username, password));
    }

    /**
     * Encode credentials to base64.
     * @param username Username
     * @param password Password
     * @return String Credentials as base64.
     */
    private static String encode(
        final String username, final String password
    ) {
        return new UncheckedScalar<>(
            () -> String.format(
                "Basic %s",
                DatatypeConverter.printBase64Binary(
                    String.format("%s:%s", username, password).getBytes("UTF-8")
                )
            )
        ).value();
    }
}
