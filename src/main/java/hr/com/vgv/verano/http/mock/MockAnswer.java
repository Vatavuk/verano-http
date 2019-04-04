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
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.HashDict;

/**
 * Mocked answer to specific request.
 * @since 1.0
 */
public class MockAnswer implements Answer {

    /**
     * Matching.
     */
    private final MatchingCriteria matching;

    /**
     * Response.
     */
    private final Dict resp;

    /**
     * Ctor.
     * @param matching Matching
     */
    public MockAnswer(final MatchingCriteria matching) {
        this(matching, new HashDict());
    }

    /**
     * Ctor.
     * @param matching Matching
     * @param inputs Inputs
     */
    public MockAnswer(final MatchingCriteria matching, final DictInput inputs) {
        this(matching, new DictOf(inputs));
    }

    /**
     * Ctor.
     * @param matching Matching
     * @param resp Response
     */
    public MockAnswer(final MatchingCriteria matching, final Dict resp) {
        this.matching = matching;
        this.resp = resp;
    }

    @Override
    public final boolean applicable(final Dict request) {
        return this.matching.apply(request).isEmpty();
    }

    @Override
    public final Dict response() {
        return this.resp;
    }
}
