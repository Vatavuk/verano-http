/**
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
package hr.com.vgv.verano.http;

import java.util.Map;
import org.cactoos.map.MapEntry;

/**
 * Key-value pair made of a string pair.
 * @since 1.0
 */
public class KvpOf implements Kvp {

    /**
     * Map entry.
     */
    private final Map.Entry<String, String> entry;

    /**
     * Ctor.
     * @param key Key
     * @param value Value
     */
    public KvpOf(final String key, final String value) {
        this(new MapEntry<>(key, value));
    }

    /**
     * Ctor.
     * @param entry Map entry
     */
    public KvpOf(final Map.Entry<String, String> entry) {
        this.entry = entry;
    }

    @Override
    public final String key() {
        return this.entry.getKey();
    }

    @Override
    public final String value() {
        return this.entry.getValue();
    }
}
