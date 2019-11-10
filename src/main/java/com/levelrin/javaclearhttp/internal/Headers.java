/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * It's responsible for analyzing the raw messages or replies to provide HTTP headers.
 *
 * Let's it analyzes below replies.
 * {@code
 * HTTP/1.1 200 OK
 * Vary: Accept-Encoding
 * Content-Length: 1256
 *
 * Content
 * }
 * It will provide the following headers:
 * {@code
 * Vary: Accept-Encoding
 * Content-Length: 1256
 * }
 */
public final class Headers {

    /**
     * Raw messages or replies in HTTP communication.
     */
    private final List<String> messages;

    /**
     * Primary constructor.
     * @param messages Info at {@link Headers#messages}.
     */
    public Headers(final List<String> messages) {
        this.messages = messages;
    }

    /**
     * Provide HTTP headers as map.
     * @return HTTP headers.
     */
    public Map<String, String> map() {
        final List<String> copy = new ArrayList<>(this.messages);
        final Map<String, String> headers = new HashMap<>();
        for (int index = 1; index < copy.size(); index = index + 1) {
            if (copy.get(index).isEmpty()) {
                break;
            }
            final String[] pair = copy.get(index).split(": ", 2);
            headers.put(pair[0], pair[1]);
        }
        return headers;
    }

}
