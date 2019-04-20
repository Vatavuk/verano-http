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

import hr.com.vgv.verano.http.Dict;
import hr.com.vgv.verano.http.DictInput;
import hr.com.vgv.verano.http.DictOf;
import hr.com.vgv.verano.http.JoinedDict;
import hr.com.vgv.verano.http.Wire;
import java.io.IOException;
import org.cactoos.iterable.IterableOf;

/**
 * Wire with additional request parameters.
 *
 * Example of usage:
 *
 * <pre>
 *    new ExpandedWire(
 *        wire,
 *        new Authorization("some token"),
 *        new Cookie("someCookie")
 *    )
 * </pre>
 *
 * @since 1.0
 */
public class ExpandedWire implements Wire {

    /**
     * Original wire.
     */
    private final Wire origin;

    /**
     * Additional request parameters.
     */
    private final Dict parameters;

    /**
     * Ctor.
     * @param wire Wire
     * @param inputs Inputs
     */
    public ExpandedWire(final Wire wire, final DictInput... inputs) {
        this(wire, new IterableOf<>(inputs));
    }

    /**
     * Ctor.
     * @param wire Wire
     * @param inputs Inputs
     */
    public ExpandedWire(final Wire wire, final Iterable<DictInput> inputs) {
        this(wire, new DictOf(inputs));
    }

    /**
     * Ctor.
     * @param wire Wire
     * @param parameters Request parameters
     */
    public ExpandedWire(final Wire wire, final Dict parameters) {
        this.origin = wire;
        this.parameters = parameters;
    }

    @Override
    public final Dict send(final Dict request) throws IOException {
        return this.origin.send(new JoinedDict(request, this.parameters));
    }
}
