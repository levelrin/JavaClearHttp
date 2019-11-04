/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Many objects have a method to send a HTTP request.
 * That means they need to construct messages for the server.
 * This class remove those redundant works.
 *
 * Also, it adds default headers.
 */
public final class Messages {

    /**
     * It's for avoiding duplicated String literals.
     */
    private static final String BODY = "body";

    /**
     * A map that contains HTTP request configuration.
     * It should have host, path, protocol, and method.
     * Also, it may have body.
     */
    private final TraceableMap map;

    /**
     * A map that contains HTTP headers.
     */
    private final Map<String, String> headers;

    /**
     * Secondary constructor.
     * It can be used when default headers are enough for a HTTP request.
     * @param map Info at {@link this#map}.
     */
    public Messages(final TraceableMap map) {
        this(map, new HashMap<>());
    }

    /**
     * Primary constructor.
     * @param map Info at {@link this#map}.
     * @param headers Info at {@link this#headers}.
     */
    public Messages(final TraceableMap map, final Map<String, String> headers) {
        this.map = map;
        this.headers = headers;
    }

    /**
     * Construct messages for HTTP request.
     * It will set default headers.
     * @return Messages.
     */
    public List<String> list() {
        final List<String> messages = new ArrayList<>();
        messages.add(this.map.value("method") + " " + this.map.value("path") + " HTTP/1.1");
        this.headers.forEach((key, value) -> messages.add(key + ": " + value));
        this.defaultHeaders().forEach((key, value) -> {
            if (!this.headers.containsKey(key)) {
                messages.add(key + ": " + value);
            }
        });
        messages.add("");
        if (this.map.contains(BODY)) {
            messages.add(this.map.value(BODY));
        }
        return messages;
    }

    /**
     * Construct default headers and return.
     * @return Default headers.
     */
    private Map<String, String> defaultHeaders() {
        final Map<String, String> given = new LinkedHashMap<>();
        given.put("Host", this.map.value("host"));
        given.put("User-Agent", "JavaClearHttp");
        given.put("Accept", "*/*");
        if (this.map.contains(BODY)) {
            given.put(
                "Content-Length",
                Integer.toString(
                    this.map.value(BODY).length()
                )
            );
            given.put("Content-Type", "application/x-www-form-urlencoded");
        }
        given.put("Connection", "close");
        return given;
    }

}
