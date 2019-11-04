/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.request;

import com.levelrin.javaclearhttp.internal.Headers;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The record of HTTP request.
 */
public final class RequestRecord implements RequestRecordType {

    /**
     * A map that contains HTTP request configuration.
     * It should have host, path, protocol, and method.
     * Also, it may have body.
     */
    private final TraceableMap map;

    /**
     * A list that contains the messages sent to the server.
     */
    private final List<String> rawMessages;

    /**
     * Primary constructor.
     * @param map Info at {@link this#map}.
     * @param messages Info at {@link this#rawMessages}.
     */
    public RequestRecord(final TraceableMap map, final List<String> messages) {
        this.map = map;
        this.rawMessages = messages;
    }

    @Override
    public Map<String, String> headers() {
        return new Headers(this.rawMessages).map();
    }

    @Override
    public String body() {
        return this.map.footprint(
            this, "body()", "Obtain body from the request record."
        ).value("body");
    }

    @Override
    public List<String> messages() {
        return new ArrayList<>(this.rawMessages);
    }

    @Override
    public String toString() {
        final List<String> copy = new ArrayList<>(this.rawMessages);
        final StringBuilder info = new StringBuilder();
        copy.forEach(message -> info.append(message).append('\n'));
        return info.toString();
    }

}
